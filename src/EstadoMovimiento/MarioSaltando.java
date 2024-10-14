package EstadoMovimiento;

import Entidades.Jugador;

public class MarioSaltando implements EstadoMovimiento {
    Jugador mario;
    private static final int VELOCIDAD_SALTO = 10;

    public MarioSaltando(Jugador mario) {
        this.mario = mario;
    }

    public void actualizar() {
        mario.set_posicion_x(mario.get_posicion_x() + mario.get_direccion() * mario.get_velocidad());
        mario.set_posicion_y(mario.get_posicion_y() + VELOCIDAD_SALTO);
    }

    public void saltar() {
        if (estaEnElSuelo()) {
            mario.setEstadoMovimiento(new MarioEnAire(mario, VELOCIDAD_SALTO));
        }
    }

    // Falta implementar
    private boolean estaEnElSuelo() {
        return mario.get_posicion_y() == 420;
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.set_posicion_x(mario.get_posicion_x() + (direccion * mario.get_velocidad()));
    }

}