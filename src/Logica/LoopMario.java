package Logica;

import Entidades.Entidad;
import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.BolaDeFuego;
import Entidades.Proyectiles.Proyectil;
import Entidades.Vacio;
import EstadoMovimiento.MarioCaminando;
import EstadoMovimiento.MarioEnAire;
import EstadoMovimiento.MarioParado;
import Fabricas.Sprite;
import Vista.Controladores.ControladorVistaJuego;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import EstadoMovimiento.MarioCaminando;
import EstadoMovimiento.MarioParado;

import Animador.AnimadorMario;


public class LoopMario implements Runnable {

    private boolean ejecutando;
    private Jugador mario;
    private int VelocidadMario;
    private ControladorVistaJuego controlador;
    private int direccionLocal = 0;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean enIdle;
    private long lastUpdateTime = System.nanoTime();
    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    protected Nivel nivel;
    protected List<Entidad> EntidadesEliminar;
    protected int cooldownBola = 60;
    protected boolean empezarCooldown;
    protected Proyectil bolaDeFuego;
    protected ControladorColisiones controladorColisiones;
    protected int timerAnimacionMorir =0;
    protected Juego juego;

    public LoopMario(Juego juego) {
        this.mario = juego.getNivelActual().getJugador();
        this.controlador = juego.getControladorVistaJuego();
        this.nivel = juego.getNivelActual();
        this.EntidadesEliminar=new ArrayList<Entidad>();
        this.controladorColisiones = new ControladorColisiones(nivel, EntidadesEliminar);
        ejecutando = false;
        this.juego = juego;
    }

    public synchronized void comenzar() {
        ejecutando = true;
        VelocidadMario = mario.getVelocidad();
        Thread hilo = new Thread(this);
        hilo.start();
    }

    public synchronized void detener() {
        ejecutando = false;
        scheduler.shutdown();
    }

    @Override
    public void run() {
        while (ejecutando) {
            long now = System.nanoTime();
            if (now - lastUpdateTime >= updateInterval) {
                lastUpdateTime = now;
                tick();
                renderizar();
            }
        }
    }

    private void iniciarTemporizadorIdle() {
        if (scheduler.isShutdown()) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                if (enIdle) {
                    mario.getSprite().setRutaImagen(AnimadorMario.MARIO_AFK);
                }
            }, 3, 3, TimeUnit.SECONDS);
        }
    }

    private void tick() {
        OyenteTeclado oyente = controlador.oyenteTeclado();
        boolean actualizacionRequerida = false;
        if(!mario.getMorir()) {
            // Movimiento lateral
            if (oyente.teclaIzquierda || oyente.teclaDerecha) {
                enIdle = false;
                if (oyente.teclaIzquierda) {
                    mario.setVelocidad(VelocidadMario);
                    mario.desplazarEnX(-1);
                    direccionLocal = -1;

                } else {
                    mario.setVelocidad(VelocidadMario);
                    mario.desplazarEnX(1);
                    direccionLocal = 1;
                }

            }
        if (!oyente.teclaIzquierda && !oyente.teclaDerecha && !oyente.teclaArriba && (mario.estaEnPlataforma()))
            mario.setEstadoMovimiento(new MarioParado(mario));


        // Logica de salto
        if (oyente.teclaArriba && (mario.estaEnPlataforma())) {
            if (mario.estaEnPlataforma())
                mario.setEnPlataforma(false);
            enIdle = false;
            mario.saltar();
            actualizacionRequerida = true;
        }
        mario.setDireccion(direccionLocal);

        //Logica para lanzar bola de fuego
        if(oyente.teclaEspacio && mario.puedeLanzarBolaDeFuego() && cooldownBola >= 30){
            cooldownBola=0;
            int mitadDeMario = (int)(mario.getHitbox().getMaxY() - (mario.getHitbox().getHeight() / 2));
            System.out.println("Quiero lanzar un proyectil");
            Sprite sprite = new Sprite("src/Recursos/Sprites/original/fireball.png", 16, 16);
            bolaDeFuego = new BolaDeFuego((int) mario.getHitbox().getMaxX(), mitadDeMario, sprite);
            bolaDeFuego.setDireccion(mario.getDireccion());
            nivel.agregarProyectil(bolaDeFuego);
            controlador.registrarEntidad(bolaDeFuego);
            empezarCooldown = true;
        }
        if (cooldownBola==20){
            bolaDeFuego.setPosicionEnY(-100);
        }

        if (empezarCooldown){
            cooldownBola++;
        }

        controladorColisiones.colisionMarioConPlataforma(nivel.getPlataformas(), mario);

        while(!EntidadesEliminar.isEmpty()) {
                nivel.getPlataformas().remove(EntidadesEliminar.getFirst());
                EntidadesEliminar.remove(EntidadesEliminar.getFirst());
        }

        controladorColisiones.colisionMarioConEnemigos(nivel.getEnemigos(), mario);

        while(!EntidadesEliminar.isEmpty()) {
            nivel.getEnemigos().remove(EntidadesEliminar.getFirst());
            EntidadesEliminar.remove(EntidadesEliminar.getFirst());
        }

        controladorColisiones.colisionMarioConMonedas(nivel.getMonedas(), mario);

        while(!EntidadesEliminar.isEmpty()) {
            nivel.getMonedas().remove(EntidadesEliminar.getFirst());
            EntidadesEliminar.remove(EntidadesEliminar.getFirst());
        }

        for (Proyectil proyectil : nivel.getProyectiles()) {
//            proyectil.actualizarEntidad();
        }

        while(!EntidadesEliminar.isEmpty()) {
            nivel.getProyectiles().remove(EntidadesEliminar.getFirst());
            EntidadesEliminar.remove(EntidadesEliminar.getFirst());
        }

        controladorColisiones.colisionMarioConPowerUps(nivel.getPowerUps(), mario);

        while(!EntidadesEliminar.isEmpty()) {
            nivel.getPowerUps().remove(EntidadesEliminar.getFirst());
            EntidadesEliminar.remove(EntidadesEliminar.getFirst());
        }

        controladorColisiones.colisionMarioConVacio(nivel.getVacios(), mario);

        while(!EntidadesEliminar.isEmpty()) {
            nivel.getVacios().remove(EntidadesEliminar.getFirst());
            EntidadesEliminar.remove(EntidadesEliminar.getFirst());
        }
        if(mario.getPosicionEnY()>460)
            mario.setMorir(true);
        mario.getEstadoJugador().actualizarSprite();
        mario.actualizarEntidad();
        mario.desplazarEnX(0);
    } else {
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioDying/AnimacionDead.gif");
        empezarCooldownMorir();
        if (timerAnimacionMorir == 100) {
            juego.perderVida();

                if (juego.getVidas()!=0) {
                    juego.reiniciar(juego.modoJuego);
                }
                else{
                    juego.mostrarPantallaFinJuego();
                }

        }


        }
        if(mario.getEstadoJugador().esInmortal()){

        }

    }

    private void empezarCooldownMorir() {
        timerAnimacionMorir++;
    }

    private void empezarCooldown() {
        cooldownBola++;
    }


    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }
}