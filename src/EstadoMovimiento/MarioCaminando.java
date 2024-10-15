package EstadoMovimiento;

import Entidades.Jugador;

public class MarioCaminando implements EstadoMovimiento {

    Jugador mario;

    public MarioCaminando(Jugador mario) {
        this.mario = mario;
    }

    @Override
    public void saltar() {
        if(estaEnElSuelo()) {
            mario.setEstadoMovimiento(new MarioSaltando(mario));
        }else{
            System.out.println("No esta en una superficie");
        }
    }

    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY() >= 420; // Suelo a nivel 420
    }
    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.get_velocidad()));
    }

    @Override
    public void actualizar() {
        mario.setPosicionEnX(mario.getPosicionEnX()+mario.get_direccion()*mario.get_velocidad());
    }
}