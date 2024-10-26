package IA;

import java.util.Random;

import Entidades.Enemigos.Enemigo;
import Logica.Temporizador;

public class IACaminar implements ComportamientoIA {
    private Random random;
    private int contadorMovimientos;
    private int direccionActual;
    private Temporizador temporizador;
    private boolean gravedad=false;

    public IACaminar() {
        random = new Random();
        contadorMovimientos = 0;
        temporizador = new Temporizador();
        temporizador.iniciar();
    }

    @Override
    public void actualizar(Enemigo enemigo) {
        contadorMovimientos++;
        if(gravedad)
            enemigo.setPosicionEnX(enemigo.getPosicionEnY()+1);
        // Cambia de dirección después de un número aleatorio de movimientos
        if(gravedad && enemigo.getPosicionEnY()>480)
            enemigo.setAEliminar();
        if(enemigo.getTemporizador().hanPasadoNSegundos(3000)) {
            temporizador.resetear();
        }
        enemigo.setPosicionEnX(enemigo.getPosicionEnX() + (enemigo.getVelocidad() * enemigo.getDireccion()));
    }

    public void activarGravedad(){
        boolean gravedad = true;
    }

    public boolean comienzoAtaque(){
        return false;
    }
}


