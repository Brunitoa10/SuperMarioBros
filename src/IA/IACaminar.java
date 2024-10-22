package IA;

import java.util.Random;

import Entidades.Enemigos.Enemigo;

public class IACaminar implements ComportamientoIA {
    private Random random;
    private int contadorMovimientos;
    private int direccionActual;

    public IACaminar() {
        random = new Random();
        contadorMovimientos = 0;
        direccionActual = random.nextBoolean() ? 1 : -1;
    }

    @Override
    public void actualizar(Enemigo enemigo) {
        contadorMovimientos++;

        // Cambia de dirección después de un número aleatorio de movimientos
        if (contadorMovimientos > random.nextInt(100)) {
            direccionActual = random.nextBoolean() ? 1 : -1;
            contadorMovimientos = 0;
        }
        enemigo.setDireccion(direccionActual);
        enemigo.setPosicionEnX(enemigo.getPosicionEnX() + (enemigo.getVelocidad() * direccionActual));
    }
}
