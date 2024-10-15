package EstadoMovimiento;

import Entidades.Jugador;

public class MarioSaltando implements EstadoMovimiento {
    private Jugador mario;
    private static final int VELOCIDAD_INICIAL_SALTO = -15; // Velocidad negativa para subir
    private static final int GRAVEDAD = 1;  // Gravedad constante que hará que baje
    private int velocidadY;  // Velocidad vertical actual

    public MarioSaltando(Jugador mario) {
        this.mario = mario;
        this.velocidadY = VELOCIDAD_INICIAL_SALTO; // Empieza subiendo con velocidad inicial
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
            mario.setPosicionEnY(420); // Ajustar la posición exacta del suelo
            mario.setEstadoMovimiento(new MarioCaminando(mario));
        }
    }

    @Override
    public void saltar() {
        // No hace nada porque ya está saltando
    }

    @Override
    public void desplazarEnX(int direccion) {
        mario.set_direccion(direccion);
    }

    public boolean estaEnElSuelo() {
        return mario.getPosicionEnY() >= 420; // Suelo a nivel 420
    }

}
