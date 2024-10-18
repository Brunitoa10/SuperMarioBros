package EstadoMovimiento;

import Entidades.Jugador;

public class MarioEnAire implements EstadoMovimiento{

    protected Jugador mario;
    protected int VELOCIDAD_SALTO;
    protected static int alturaMax;
    protected int piso;
    private static final int GRAVEDAD = 1; // Gravedad constante que hará que baje
    private int velocidadY;

    public MarioEnAire(Jugador mario) {
        this.mario = mario;
        alturaMax = mario.getPosicionEnY()+50;
        piso= mario.getPiso();
    }

    @Override
    public void saltar(Jugador mario) {
        System.out.println("Mario en el aire");
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
        mario.setPosicionEnX(mario.getPosicionEnX() + (direccion * mario.get_velocidad()));
    }

    @Override
    public void actualizar() {
        // Movimiento horizontal
        mario.setPosicionEnX(mario.getPosicionEnX() + mario.get_direccion() * mario.get_velocidad());

        // Aplicar gravedad (para que empiece a bajar eventualmente)
        velocidadY += GRAVEDAD;

        // Movimiento vertical
        mario.setPosicionEnY(mario.getPosicionEnY() + velocidadY);

        // Verificar si Mario toca el suelo
        if (estaEnElSuelo()) {

            // Cambiar al estado de caminar si está en el suelo
            mario.setEstadoMovimiento(new MarioCaminando(mario));

        }
    }

    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY()==mario.getPiso();
    }

}
