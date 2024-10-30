package EstadoMovimiento;

import Entidades.Jugador;

public class MarioEnAire implements EstadoMovimiento {

    private static final int GRAVEDAD = 1;
    private static final int TOPE_GRAVEDAD = 15;
    protected Jugador mario;
    private int velocidadY;

    public MarioEnAire(Jugador mario, int velocidadY) {
        this.mario = mario;
        this.velocidadY = velocidadY;

    }

    @Override
    public void saltar() {

    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setDireccion(direccion);
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.getVelocidad()));
    }

    @Override
    public void actualizar() {
        mario.getEstadoJugador().actualizarEstadoJugador();

        if (velocidadY <= TOPE_GRAVEDAD) {
            velocidadY += GRAVEDAD;
        }

        mario.setPosicionEnY(mario.getPosicionEnY() + velocidadY);

        if (mario.getDireccion() == 1) {
            mario.getSprite().setRutaImagen(mario.getEstadoJugador().inicioAnimacion() + "/JumpingMarioRigth.png" + mario.getEstadoJugador().finalAnimacion());
        } else {
            mario.getSprite().setRutaImagen(mario.getEstadoJugador().inicioAnimacion() + "/JumpingMarioLeft.png" + mario.getEstadoJugador().finalAnimacion());
        }
    }

    public void LanzarBola() {
        mario.setEstadoMovimiento(new LanzandoBola(mario));
    }

    public void EnAire() {
    }

    @Override
    public void AFK() {
    }

}
