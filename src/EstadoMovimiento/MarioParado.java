package EstadoMovimiento;

import Entidades.Jugador;

public class MarioParado implements EstadoMovimiento {
    Jugador mario;

    public MarioParado(Jugador mario) {
        this.mario = mario;
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
        mario.setPosicionEnX(mario.getPosicionEnX()+mario.get_direccion()*mario.get_velocidad());
    }

    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY()==mario.getPiso();
    }
}
