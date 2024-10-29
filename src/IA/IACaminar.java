package IA;


import Entidades.Enemigos.Enemigo;
import Entidades.Enemigos.PiranhaPlant;
import Logica.Temporizador;

public class IACaminar implements ComportamientoIA {
   
    protected Temporizador temporizador;
    protected boolean gravedad;
    protected int yMaximo;
    protected int yEscondido;

    public IACaminar() {
        temporizador = new Temporizador();
        temporizador.iniciar();
        gravedad = false;
    }

    @Override
    public void actualizar(Enemigo enemigo) {
        if(gravedad) {
            enemigo.setPosicionEnX(enemigo.getPosicionEnY()+1);
        }
       
        if(gravedad && enemigo.getPosicionEnY()>480) {
            enemigo.setAEliminar();
        }
        if(enemigo.getTemporizador().hanPasadoNSegundos(3000)) {
            temporizador.resetear();
        }
        enemigo.setPosicionEnX(enemigo.getPosicionEnX() + (enemigo.getVelocidad() * enemigo.getDireccion()));
    }

    public void actualizarPiranha(Enemigo enemigo) {

    }

    public boolean comienzoAtaque(){
        return false;
    }
}


