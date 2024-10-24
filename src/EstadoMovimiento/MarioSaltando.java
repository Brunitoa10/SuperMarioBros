package EstadoMovimiento;

import Animador.AnimadorMario;
import Entidades.Jugador;

public class MarioSaltando implements EstadoMovimiento {
   /* private Jugador mario;
    private static final int VELOCIDAD_INICIAL_SALTO = -18; // Velocidad negativa para subir
    private static final int GRAVEDAD = 1; // Gravedad constante que hará que baje
    private int velocidadY; // Velocidad vertical actual
    protected int Piso;

    public MarioSaltando(Jugador mario) {

        this.velocidadY = VELOCIDAD_INICIAL_SALTO;
        this.mario = mario;// Empieza subiendo con velocidad inicial
        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png"+mario.getEstadoJugador().finalAnimacion());
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png"+mario.getEstadoJugador().finalAnimacion());
        }
    }

    @Override
    public void actualizar() {
        // Movimiento horizontal
        mario.getEstadoJugador().actualizarSprite();

        // Aplicar gravedad (para que empiece a bajar eventualmente)
        velocidadY += GRAVEDAD;

        // Movimiento vertical
        mario.setPosicionEnY(mario.getPosicionEnY() + velocidadY);

        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png"+mario.getEstadoJugador().finalAnimacion());
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png"+mario.getEstadoJugador().finalAnimacion());
        }

    }

    public void saltar(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(VELOCIDAD_INICIAL_SALTO+ mario.getPosicionEnY());
        EnAire(mario);
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setPosicionEnX(mario.getPosicionEnX()+direccion*mario.getVelocidad());
    }

    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY()+mario.getHitbox().getHeight()==mario.getPiso()+32;
    }

    public void LanzarBola() {
        mario.setEstadoMovimiento(new LanzandoBola(mario));
    }

    public void EnAire(Jugador jugador) {
        mario.setEstadoMovimiento(new MarioEnAire(mario,velocidadY));
    }

    @Override
    public void AFK(Jugador jugador) {
        mario.setEstadoMovimiento(new MarioParado(mario));
    }

*/
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
