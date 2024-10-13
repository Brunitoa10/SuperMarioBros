package EstadoMovimiento;

import Entidades.Jugador;

public class MarioEnAire implements EstadoMovimiento{

    protected Jugador mario;
    protected int VELOCIDAD_SALTO;
    protected static int alturaMax;

    public MarioEnAire(Jugador mario,int Velocidad) {
        VELOCIDAD_SALTO = Velocidad;
        this.mario = mario;
        alturaMax = mario.get_posicion_y()+50;
    }

    @Override
    public void saltar() {
        System.out.println("Mario en el aire");
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.set_posicion_x(mario.get_posicion_x() + (direccion * mario.get_velocidad()));
    }

    @Override
    public void actualizar() {
        mario.set_posicion_x(mario.get_posicion_x()+mario.get_direccion()*mario.get_velocidad());
        if(mario.get_posicion_y()>=alturaMax) {
            mario.set_posicion_y(mario.get_posicion_y() - VELOCIDAD_SALTO);
        }else{
            mario.set_posicion_y(mario.get_posicion_y() + VELOCIDAD_SALTO);
        }
    }
}
