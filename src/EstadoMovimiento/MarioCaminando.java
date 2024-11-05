package EstadoMovimiento;

import Entidades.Jugador;

public class MarioCaminando implements EstadoMovimiento {

    private Jugador mario;

    public MarioCaminando(Jugador mario) {
        this.mario = mario;
        mario.getEstadoJugador().actualizarEstadoJugador();
        mario.setPosicionEnX(mario.getPosicionEnX() + mario.getVelocidad() * mario.getDireccion());
    }

    @Override
    public void saltar() {
        mario.setEstadoMovimiento(new MarioSaltando(mario));

    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setDireccion(direccion);
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.getVelocidad()));
    }

    @Override
    public void actualizar() {
        mario.getEstadoJugador().actualizarEstadoJugador();
        if (mario.getDireccion() == 1) {
            mario.getSprite().setRutaImagen(mario.getEstadoJugador().inicioAnimacion()+ "/RunningLoop/MarioCaminandoRight.gif" + mario.getEstadoJugador().finalAnimacion());
        } else {
            mario.getSprite().setRutaImagen(mario.getEstadoJugador().inicioAnimacion() + "/RunningLoop/MarioCaminandoLeft.gif" + mario.getEstadoJugador().finalAnimacion());
        }
    }

    public void LanzarBola() {
        mario.setEstadoMovimiento(new LanzandoBola(mario));
    }

    public void EnAire() {
        mario.setEstadoMovimiento(new MarioEnAire(mario, 0));
    }

    @Override
    public void AFK() {
        mario.setEstadoMovimiento(new MarioParado(mario));
    }
}