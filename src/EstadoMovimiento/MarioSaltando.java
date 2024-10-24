package EstadoMovimiento;

import Animador.AnimadorMario;
import Entidades.Jugador;

public class MarioSaltando implements EstadoMovimiento {

	private static final int VELOCIDAD_INICIAL_SALTO = -15; // Velocidad negativa para subir
	private static final int GRAVEDAD = 1; // Gravedad constante que hará que baje


	private Jugador mario;
	private int velocidadY;

	public MarioSaltando(Jugador mario) {
		this.mario = mario;
		this.velocidadY = VELOCIDAD_INICIAL_SALTO; // Comienza subiendo

		// Asegurarse de que Mario no esté en la plataforma
		if (mario.estaEnPlataforma()) {
			mario.setEnPlataforma(false);
		}
		actualizarRutaImagen(); // Inicializa la imagen
	}

	@Override
	public void actualizar() {
		mario.getEstadoJugador().actualizarSprite(); // Actualiza el sprite

		// Aplicar gravedad
		velocidadY += GRAVEDAD;

		// Movimiento vertical
		mario.setPosicionEnY(mario.getPosicionEnY() + velocidadY);

		actualizarRutaImagen(); // Actualiza la imagen durante el salto
	}

	@Override
	public void desplazarEnX(int direccion) {
		mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.getVelocidad()));
	}

	@Override
	public boolean estaEnElSuelo() {
		return false; // No se puede estar en el suelo mientras está saltando
	}

	private void actualizarRutaImagen() {
		String rutaImagen = (mario.getDireccion() == 1) ? AnimadorMario.RUTA_MARIO_JUMP_DERECHA : AnimadorMario.RUTA_MARIO_JUMP_IZQUIERDA;
		mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen() + rutaImagen + mario.getEstadoJugador().finalAnimacion());
	}

	//Codigo inalcanzable
	@Override
	public void saltar() {
		mario.setPosicionEnY(VELOCIDAD_INICIAL_SALTO + mario.getPosicionEnY());
		mario.setEstadoMovimiento(new MarioEnAire(mario)); // Cambia a estado de aire
	}
}
