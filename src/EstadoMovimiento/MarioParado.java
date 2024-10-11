package EstadoMovimiento;

import Entidades.Jugador;

public class MarioParado implements EstadoMovimiento {
    Jugador mario;

    public MarioParado(Jugador mario) {
        this.mario = mario;
    }

    @Override
    public void saltar() {
        mario.setEstadoMovimiento(new MarioSaltando(mario));
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setEstadoMovimiento(new MarioCaminando(mario));
    }
}
