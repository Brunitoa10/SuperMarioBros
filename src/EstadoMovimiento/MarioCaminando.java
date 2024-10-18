package EstadoMovimiento;

import Entidades.Jugador;

public class MarioCaminando implements EstadoMovimiento {

    Jugador mario;

    public MarioCaminando(Jugador mario) {
        this.mario = mario;
    }

    @Override
    public void saltar(Jugador mario) {

            mario.setEstadoMovimiento(new MarioSaltando(mario));
    }


    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.get_velocidad()));
    }

    @Override
    public void actualizar() {
    }

    @Override
    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY()==mario.getPiso();
    }

}