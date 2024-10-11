package EstadoMovimiento;

import Entidades.Jugador;

public class MarioSaltando implements EstadoMovimiento {
    Jugador mario;

    public MarioSaltando(Jugador mario) {
        this.mario = mario;
    }

    @Override
    public void saltar() {

    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.set_posicion_x(mario.get_posicion_x() + (direccion * mario.get_velocidad()));
    }
}
