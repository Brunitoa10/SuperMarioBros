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
    private static final int SUELO_Y = 420;
    private int direccionLocal = 0;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean enIdle;
    private long lastUpdateTime = System.nanoTime();
    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    protected boolean caerAlInfinito = false;
    protected boolean EstadoActivado = false;
    protected Nivel nivel;
    protected PartidaActual partidaActual;
    protected List<Entidad> EntidadesEliminar;

    public LoopMario(Juego juego) {
        this.mario = juego.getNivelActual().getJugador();
        this.controlador = juego.getControladorVistaJuego();
        this.nivel = juego.getNivelActual();
        this.EntidadesEliminar=new ArrayList<Entidad>();
        ejecutando = false;
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
        if(!mario.estaVivo()) {
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
        if(oyente.teclaEspacio && mario.puedeLanzarBolaDeFuego()) {
            System.out.println("Quiero lanzar un proyectil");
            Sprite sprite = new Sprite("src/Recursos/Sprites/original/fireball.png", 16, 16);
            BolaDeFuego bolaDeFuego = new BolaDeFuego((int) mario.getHitbox().getMaxX(), (int) (mario.getHitbox().getMaxY() - (mario.getHitbox().getHeight() / 2)), sprite);
            bolaDeFuego.setDireccion(mario.getDireccion());
            nivel.agregarProyectil(bolaDeFuego);
            controlador.registrarEntidad(bolaDeFuego);
        }

        for (Plataforma p : nivel.getPlataformas()) {
            if (mario.detectarColision((p))) {
                int PosicionReemplazarX=p.getPosicionEnX();
                int PosicionReemplazarY=p.getPosicionEnY();
                mario.getVisitorJugador().visit(p);
                p.actualizarEntidad();
                if(p.aEliminar()){
                    nivel.getVacios().add(new Vacio(PosicionReemplazarX,PosicionReemplazarY,new Sprite("",32,32)));
                    EntidadesEliminar.add(p);
                }
            }
        }

        for (Enemigo e : nivel.getEnemigos()) {
            if(mario.detectarColision(e)) {
                e.getVisitorEnemigo().visit(mario);
                e.actualizarEntidad();
            }
        }

        for(Moneda m : nivel.getMonedas()) {
            if(mario.detectarColision(m)) {
                mario.getVisitorJugador().visit(m);
                m.actualizarEntidad();
            }
        }

        for (Proyectil proyectil : nivel.getProyectiles()) {
            proyectil.actualizarEntidad();
        }

        for (PowerUp p : nivel.getPowerUps()) {
            if (mario.detectarColision((p))) {
                p.getVisitor().visit(mario);
                p.actualizarEntidad();
                actualizacionRequerida = true;
                mario.getEstadoJugador().actualizarSprite();
                EntidadesEliminar.add(p);
            }
        }

        for (Vacio vacio : nivel.getVacios()) {
            if (VacioColisionoAbajo(vacio)) {

                if (mario.estaEnPlataforma()) {
                    mario.setEstadoMovimiento(new MarioEnAire(mario));
                    mario.setEnPlataforma(false);
                }

                //hacer
                if (mario.getPosicionEnY() == SUELO_Y) {
                    caerAlInfinito = true;
                    mario.setEstadoMovimiento(new MarioEnAire(mario));

                }

            }
        }
        if(mario.getPosicionEnY()>460)
            mario.setEstaVivo(false);

        while(!EntidadesEliminar.isEmpty()) {
            EntidadesEliminar.remove(EntidadesEliminar.getFirst());
        }
            mario.getEstadoJugador().actualizarSprite();
            mario.actualizarEntidad();
            mario.desplazarEnX(0);
    } else {
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioDying/AnimacionDead.gif");
    }
    }


    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }

    private boolean VacioColisionoAbajo(Vacio vacio) {
        boolean Colisiono = false;
        int toleranciaX=5;
        if ((mario.getPosicionEnX() >= vacio.getPosicionEnX()-toleranciaX) && mario.getPosicionEnX()+mario.getHitbox().getWidth() <= vacio.getPosicionEnX()+vacio.getHitbox().getWidth()+toleranciaX ) {
            if ((mario.getHitbox().getMaxY() == vacio.getPosicionEnY())) {
                Colisiono = true;
            }
        }
        return Colisiono;
    }
}