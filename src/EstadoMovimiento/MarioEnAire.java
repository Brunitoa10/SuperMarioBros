package EstadoMovimiento;

import Entidades.Jugador;

public class MarioEnAire implements EstadoMovimiento{

    protected Jugador mario;
    protected int VELOCIDAD_SALTO;
    protected static int alturaMax;

    public MarioEnAire(Jugador mario,int Velocidad) {
        VELOCIDAD_SALTO = Velocidad;
        this.mario = mario;
        alturaMax = mario.getPosicionEnY()+50;
    }

    @Override
    public void saltar(Jugador mario) {
        this.mario = mario;
        System.out.println("Mario en el aire");
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.get_velocidad()));
    }

    @Override
    public void actualizar() {
        mario.setPosicionEnX(mario.getPosicionEnX()+mario.get_direccion()*mario.get_velocidad());
        if(mario.getPosicionEnY()>=alturaMax) {
            if(mario.getPosicionEnY()>420) {
                mario.setPosicionEnY(mario.getPosicionEnY() - VELOCIDAD_SALTO);
                if(mario.getPosicionEnY()==420) {
                    VELOCIDAD_SALTO=0;
                    mario.setEstadoMovimiento(new MarioParado(mario));
                }
            }
        }else{
            mario.setPosicionEnY(mario.getPosicionEnY() + VELOCIDAD_SALTO);
        }
    }
    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY() >= mario.getPiso(); // Suelo a nivel 420
    }
}
