package EstadoMovimiento;

import Entidades.Jugador;

public class MarioSaltando implements EstadoMovimiento {
    Jugador mario;
    private static final int VELOCIDAD_INICIAL_SALTO = 10;
    private static final int GRAVEDAD = 2;//Declarar en otro lado
    private int velocidadSalto;
    private boolean ascendiendo;

    public MarioSaltando(Jugador mario) {
        this.mario = mario;
        velocidadSalto = VELOCIDAD_INICIAL_SALTO;
    }

    public void actualizar() {
        if (mario.estaSaltando()) {
            velocidadSalto -= GRAVEDAD;
            ascendiendo = velocidadSalto > 0; 
            mario.set_posicion_y(mario.get_posicion_y() + velocidadSalto);
        }
        if (estaEnElSuelo()) {
            mario.setEstaSaltando(false);
            velocidadSalto = 0;
            ascendiendo = false; 
        }
    }

    public void saltar() {
        if(mario.estaSaltando()) {
            return;
        }
        mario.setEstaSaltando(true);
        velocidadSalto = VELOCIDAD_INICIAL_SALTO;
        ascendiendo = true;
    }

    //Falta mplementar
    private boolean estaEnElSuelo() {
        return true;
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.set_posicion_x(mario.get_posicion_x() + (direccion * mario.get_velocidad()));
    }

}
