package EstadoMovimiento;

import Entidades.Jugador;

public class MarioParado implements EstadoMovimiento {
    private final Jugador mario;

    public MarioParado(Jugador mario) {
        this.mario = mario;
        mario.getEstadoJugador().actualizarEstadoJugador();
    }

    @Override
    public void saltar() {
        System.out.println("Aprete W en MarioParado");
        mario.setEstadoMovimiento(new MarioSaltando(mario));

    }

    @Override
    public void desplazarEnX(int direccion) {

        mario.setEstadoMovimiento(new MarioCaminando(mario));
    }


    public void actualizar() {

        mario.getEstadoJugador().actualizarEstadoJugador();
        if (mario.getDireccion() == 1) {
            mario.getSprite().setRutaImagen(mario.getEstadoJugador().inicioAnimacion() + "/StandingMarioRigth.png" + mario.getEstadoJugador().finalAnimacion());
        } else {
            mario.getSprite().setRutaImagen(mario.getEstadoJugador().inicioAnimacion() + "/StandingMarioLeft.png" + mario.getEstadoJugador().finalAnimacion());
        }
    }

    @Override
    public void LanzarBola() {
        mario.setEstadoMovimiento(new LanzandoBola(mario));
    }

    public void EnAire() {
        mario.setEstadoMovimiento(new MarioEnAire(mario, 0));
    }

    public void AFK() {
    }


}
