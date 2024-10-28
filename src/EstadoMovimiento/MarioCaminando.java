package EstadoMovimiento;

import Entidades.Jugador;

public class MarioCaminando implements EstadoMovimiento {

    private Jugador mario;

    public MarioCaminando(Jugador mario) {
        this.mario = mario;
        mario.getEstadoJugador().actualizarSprite();
        mario.setPosicionEnX(mario.getPosicionEnX() + mario.getVelocidad() * mario.getDireccion());
        System.out.println("Me cree por alguna razon");
        if (mario.getDireccion() == 1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen() + "/RunningLoop/MarioCaminandoRight.gif" + mario.getEstadoJugador().finalAnimacion());
        } else {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen() + "/RunningLoop/MarioCaminandoLeft.gif" + mario.getEstadoJugador().finalAnimacion());
        }
    }

    @Override
    public void saltar() {
        System.out.println("Aprete W");
        mario.setEstadoMovimiento(new MarioSaltando(mario));

    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setDireccion(direccion);
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.getVelocidad()));
    }

    @Override
    public void actualizar() {
        mario.getEstadoJugador().actualizarSprite();
        if (mario.getDireccion() == 1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen() + "/RunningLoop/MarioCaminandoRight.gif" + mario.getEstadoJugador().finalAnimacion());
        } else {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen() + "/RunningLoop/MarioCaminandoLeft.gif" + mario.getEstadoJugador().finalAnimacion());
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