package EstadoMovimiento;

import Entidades.Jugador;

public class MarioEnAire implements EstadoMovimiento{

    protected Jugador mario;
    protected int VELOCIDAD_SALTO;
    protected static int alturaMax;
    public int piso;

    public MarioEnAire(Jugador mario,int Velocidad) {
        VELOCIDAD_SALTO = Velocidad;
        this.mario = mario;
        alturaMax = mario.getPosicionEnY()+50;
        piso= mario.get_piso();
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
        piso=mario.get_piso();
        mario.setPosicionEnX(mario.getPosicionEnX()+mario.get_direccion()*mario.get_velocidad());
        if(mario.getPosicionEnY()>=alturaMax) {
            if(mario.getPosicionEnY()>mario.getPiso()) {
                mario.setPosicionEnY(mario.getPosicionEnY() - VELOCIDAD_SALTO);
                if(mario.getPosicionEnY()<=mario.getPiso()) {

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
