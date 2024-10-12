package EstadoMovimiento;

import Entidades.Jugador;

public class MarioSaltando implements EstadoMovimiento {
    Jugador mario;
    private static final int VELOCIDAD_INICIAL_SALTO = 10;
    private static final int GRAVEDAD = 2;//Declarar en otro lado
    private int velocidadSalto;

    public MarioSaltando(Jugador mario) {
        this.mario = mario;
        velocidadSalto = VELOCIDAD_INICIAL_SALTO;
    }

    public void actualizar() {
        if(mario.estaSaltando()) {
            mario.set_posicion_y(mario.get_posicion_y() + mario.getVelocidadSalto());
        }
    }

    private boolean estaEnELSuelo() {
        return mario.get_posicion_y()==0;
    }

    @Override
    public void saltar() {
        if(mario.estaSaltando()) {
            return;
        }
        mario.setEstaSaltando(true);
        mario.setVelocidadSalto(VELOCIDAD_INICIAL_SALTO);
        mario.setAlturaMaximaSalto(mario.getAlturaMaximaSalto());
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.set_posicion_x(mario.get_posicion_x() + (direccion * mario.get_velocidad()));
    }
}
