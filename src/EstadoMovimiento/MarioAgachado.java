package EstadoMovimiento;

import Entidades.Jugador;

public class MarioAgachado implements EstadoMovimiento {

    Jugador mario;

    public MarioAgachado(Jugador mario) {
        this.mario = mario;
    }

    @Override
    public void saltar() {

    }

    @Override
    public void desplazarEnX(int direccion) {
    }
}
