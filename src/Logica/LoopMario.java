package Logica;

import Entidades.Jugador;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class LoopMario implements Runnable {

	private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
	protected ControladorColisiones controladorColisiones;
	protected int timerAnimacionMorir = 0;
	protected Juego juego;
	protected Temporizador temporizador;
	protected boolean debeSaltar;
	protected boolean FrenoElTick;
	protected Temporizador temporizador2;
	private boolean ejecutando;
	private Jugador mario;
	private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	private boolean enIdle;
	private long lastUpdateTime = System.nanoTime();

	public LoopMario(Juego juego) {
		this.mario = juego.getNivelActual().getJugador();
		this.controladorColisiones = new ControladorColisiones(juego.getNivelActual(), juego);
		this.juego = juego;
		inicializarTemporizadores();
		inicializarBooleanos();
	}

	private void inicializarBooleanos() {
		debeSaltar = false;
		ejecutando = false;
		FrenoElTick = false;
	}

	private void inicializarTemporizadores() {
		temporizador = new Temporizador();
		temporizador2 = new Temporizador();
	}

	public synchronized void comenzar() {
		ejecutando = true;
		Thread hilo = new Thread(this);
		hilo.start();
		temporizador.iniciar();
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

	private void tick() {
		if (!FrenoElTick) {
			if (!mario.getMorir()) {
				juego.moverMario(temporizador);
				if(mario.getPosicionEnX() > 6350){
					juego.nivelSiguiente();
				}
				juego.lanzarBolasDeFuego(mario);
				FrenoElTick = controladorColisiones.colisionesMario();
				juego.eliminarEntidades();
				if (mario.getPosicionEnY() > 460) {
					mario.setMorir(true);
				}

				juego.checkearSumaVida();

				mario.getEstadoJugador().actualizarSprite();
				mario.actualizarEntidad();
			} else {
				juego.mostrarMarioMuerte(mario);
				empezarCooldownMorir();
				if (timerAnimacionMorir == 100) {
					juego.manejarMuerte();
				}
			}
		} else {
			temporizador2.iniciar();
			juego.frenarHilos();
			if (mario.getDireccion() == -1)
				mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp/ConsumePowerUp/ConsumoHongoLeft.gif");
			if (mario.getDireccion() == +1)
				mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp/ConsumePowerUp/ConsumoHongoRigth.gif");
			if (temporizador2.hanPasadoNSegundos(2000)) {
				FrenoElTick = false;
				juego.reanudarHilo();
			}
		}
	}

	private void empezarCooldownMorir() {
		timerAnimacionMorir++;
	}

	private void renderizar() {
		juego.getControladorVistaJuego().actualizarObserver();
	}
}