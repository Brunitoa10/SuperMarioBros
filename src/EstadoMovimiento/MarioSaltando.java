package EstadoMovimiento;

import Constantes.AnimadorMario;
import Entidades.Jugador;

public class MarioSaltando implements EstadoMovimiento {

    private static final int VELOCIDAD_INICIAL_SALTO = -18; // Velocidad negativa para subir
    private static final int GRAVEDAD = 1;
    private final Jugador mario;
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
        if (velocidadY <= 15)
            velocidadY += GRAVEDAD;

        // Movimiento vertical
        mario.setPosicionEnY(mario.getPosicionEnY() + velocidadY);

        actualizarRutaImagen(); // Actualiza la imagen durante el salto
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.getVelocidad()));
    }

    private void actualizarRutaImagen() {
        String rutaImagen = (mario.getDireccion() == 1) ? AnimadorMario.RUTA_MARIO_JUMP_DERECHA : AnimadorMario.RUTA_MARIO_JUMP_IZQUIERDA;
        mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen() + rutaImagen + mario.getEstadoJugador().finalAnimacion());
    }

    public void LanzarBola() {
        mario.setEstadoMovimiento(new LanzandoBola(mario));
    }

    public void EnAire() {
        mario.setEstadoMovimiento(new MarioEnAire(mario, velocidadY));
    }

    @Override
    public void AFK() {
        mario.setEstadoMovimiento(new MarioParado(mario));
    }

    @Override
    public void saltar() {
        mario.setPosicionEnY(VELOCIDAD_INICIAL_SALTO + mario.getPosicionEnY());
        mario.setEstadoMovimiento(new MarioEnAire(mario, VELOCIDAD_INICIAL_SALTO)); // Cambia a estado de aire
    }
}
