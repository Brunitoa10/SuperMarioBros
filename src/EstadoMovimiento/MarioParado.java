package EstadoMovimiento;

import Entidades.Jugador;

public class MarioParado implements EstadoMovimiento {
    Jugador mario;

    public MarioParado(Jugador mario) {
        this.mario = mario;
        mario.getEstadoJugador().actualizarSprite();
        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/StandingMarioRigth.png"+mario.getEstadoJugador().finalAnimacion());
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/StandingMarioLeft.png"+mario.getEstadoJugador().finalAnimacion());
        }
    }

    @Override
    public void saltar(Jugador mario) {
            mario.setEstadoMovimiento(new MarioSaltando(mario));
    }

    @Override
    public void desplazarEnX(int direccion) {

        mario.setEstadoMovimiento(new MarioCaminando(mario));
    }


    public void actualizar() {

        mario.getEstadoJugador().actualizarSprite();
        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/StandingMarioRigth.png"+mario.getEstadoJugador().finalAnimacion());
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/StandingMarioLeft.png"+mario.getEstadoJugador().finalAnimacion());
        }
    }

    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY()+mario.getHitbox().getHeight()==mario.getPiso()+32;
    }

    @Override
    public void LanzarBola() {
        mario.setEstadoMovimiento(new LanzandoBola(mario));
    }

    public void EnAire(Jugador jugador) {
        mario.setEstadoMovimiento(new MarioEnAire(jugador,0));
    }

    public void AFK(Jugador jugador) {
    }


}
