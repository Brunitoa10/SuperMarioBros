package EstadoMovimiento;

import Entidades.Jugador;

public class MarioCaminando implements EstadoMovimiento {

    Jugador mario;

    public MarioCaminando(Jugador mario) {
        this.mario = mario;
        mario.getEstadoJugador().actualizarSprite();
        mario.setPosicionEnX(mario.getPosicionEnX()+mario.getVelocidad()*mario.getDireccion());
        System.out.println("Me cree por alguna razon");
        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/RunningLoop/MarioCaminandoRight.gif"+mario.getEstadoJugador().finalAnimacion());
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/RunningLoop/MarioCaminandoLeft.gif"+mario.getEstadoJugador().finalAnimacion());
        }
    }

    @Override
    public void saltar(Jugador mario) {
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
        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/RunningLoop/MarioCaminandoRight.gif"+mario.getEstadoJugador().finalAnimacion());
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/RunningLoop/MarioCaminandoLeft.gif"+mario.getEstadoJugador().finalAnimacion());
        }
    }

    @Override
    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY()+mario.getHitbox().getHeight()==mario.getPiso()+32;
    }

    public void LanzarBola() {
        mario.setEstadoMovimiento(new LanzandoBola(mario));
    }
}