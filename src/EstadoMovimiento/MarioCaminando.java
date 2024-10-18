package EstadoMovimiento;

import Entidades.Jugador;

public class MarioCaminando implements EstadoMovimiento {

    Jugador mario;

    public MarioCaminando(Jugador mario) {
        this.mario = mario;
    }

    @Override
    public void saltar(Jugador mario) {
        this.mario = mario;
        if(estaEnElSuelo()) {
            mario.setEstadoMovimiento(new MarioSaltando(mario));
        }else{
            System.out.println("No esta en una superficie");
        }
    }

    public boolean estaEnElSuelo() {
        return true;
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.get_velocidad()));
    }

    @Override
    public void actualizar() {
    }
}