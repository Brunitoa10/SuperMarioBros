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
    public void saltar() {
        System.out.println("Mario en el aire");
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setDireccion(direccion);
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.getVelocidad()));
    }

    @Override
    public void actualizar() {
        mario.setPosicionEnX(mario.getPosicionEnX()+mario.getDireccion()*mario.getVelocidad());
        if(mario.getPosicionEnY()>=alturaMax) {
            mario.setPosicionEnY(mario.getPosicionEnY() - VELOCIDAD_SALTO);
        }else{
            mario.setPosicionEnY(mario.getPosicionEnY() + VELOCIDAD_SALTO);
        }
    }
    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY() >= 420; // Suelo a nivel 420
    }
}
