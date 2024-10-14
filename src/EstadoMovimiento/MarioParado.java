package EstadoMovimiento;

import Entidades.Jugador;

public class MarioParado implements EstadoMovimiento {
    Jugador mario;

    public MarioParado(Jugador mario) {
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
        return mario.get_posicion_y() >= 420; // Suelo a nivel 420
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setEstadoMovimiento(new MarioCaminando(mario));
    }


    public void actualizar() {
        mario.set_posicion_x(mario.get_posicion_x()+mario.get_direccion()*mario.get_velocidad());
    }
}
