package EstadoMovimiento;

import Entidades.Jugador;

public class MarioSaltando implements EstadoMovimiento {
    private Jugador mario;
    private static final int VELOCIDAD_INICIAL_SALTO = -15; // Velocidad negativa para subir
    private static final int GRAVEDAD = 1; // Gravedad constante que hará que baje
    private int velocidadY; // Velocidad vertical actual
    protected int Piso;

    public MarioSaltando(Jugador mario) {

        this.velocidadY = VELOCIDAD_INICIAL_SALTO;
        this.mario = mario;// Empieza subiendo con velocidad inicial
        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png");
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png");
        }
    }

    @Override
    public void actualizar() {
        // Movimiento horizontal
        mario.getEstadoJugador().actualizarSprite();

        // Aplicar gravedad (para que empiece a bajar eventualmente)
        velocidadY += GRAVEDAD;

        // Movimiento vertical
        mario.setPosicionEnY(mario.getPosicionEnY() + velocidadY);

        if(mario.getDireccion()==1) {
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioRigth.png");
        }else{
            mario.getSprite().setRutaImagen(mario.getSprite().getRutaImagen()+"/JumpingMarioLeft.png");
        }

    }

    public void saltar(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(VELOCIDAD_INICIAL_SALTO+ mario.getPosicionEnY());
        mario.setEstadoMovimiento(new MarioEnAire(mario));
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.setPosicionEnX(mario.getPosicionEnX()+direccion*mario.getVelocidad());
    }

    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY()+mario.getHitbox().getHeight()==mario.getPiso()+32;
    }

}
