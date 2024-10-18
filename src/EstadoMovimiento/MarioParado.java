package EstadoMovimiento;

import Entidades.Jugador;

public class MarioParado implements EstadoMovimiento {
    Jugador mario;

    public MarioParado(Jugador mario) {
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
        return true; // Suelo a nivel 420
         }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setEstadoMovimiento(new MarioCaminando(mario));
    }


    public void actualizar() {
        mario.setPosicionEnX(mario.getPosicionEnX()+mario.get_direccion()*mario.get_velocidad());
    }
}
