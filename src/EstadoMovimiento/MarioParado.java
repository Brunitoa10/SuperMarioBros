package EstadoMovimiento;

import Entidades.Jugador;

public class MarioParado implements EstadoMovimiento {
    Jugador mario;

    public MarioParado(Jugador mario) {
        System.out.println("MarioParado");
        this.mario = mario;
        mario.getEstadoJugador().actualizarSprite();
        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/StandingMarioRigth.png");
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/StandingMarioLeft.png");
        }
    }

    @Override
    public void saltar(Jugador mario) {
            mario.setEstadoMovimiento(new MarioSaltando(mario));
    }

    @Override
    public void desplazarEnX(int direccion) {
        if (direccion!=0) {
            mario.setEstadoMovimiento(new MarioCaminando(mario));
        }
    }


    public void actualizar() {

        mario.getEstadoJugador().actualizarSprite();
        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/StandingMarioRigth.png");
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/StandingMarioLeft.png");
        }
    }

    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY()+mario.getHitbox().getHeight()==mario.getPiso()+32;
    }
}
