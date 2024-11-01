package IA;


import Entidades.Enemigos.Enemigo;
import Logica.Temporizador;

public class IACaminar implements ComportamientoIA {
   
    protected Temporizador temporizador;
    protected boolean gravedad;

    public IACaminar() {
        temporizador = new Temporizador();
        temporizador.iniciar();
        gravedad = false;
    }

    @Override
    public void actualizar(Enemigo enemigo) {
        if(!enemigo.estoyEnPlataforma()) {
            enemigo.setPosicionEnY(enemigo.getPosicionEnY()+2);
        }
       
        if(!enemigo.estoyEnPlataforma() && enemigo.getPosicionEnY()>520) {
            enemigo.setAEliminar();
        }
        if(enemigo.getTemporizador().hanPasadoNSegundos(3000)) {
            temporizador.resetear();
        }
        if (enemigo.estoyEnPlataforma()) {
            enemigo.setPosicionEnX(enemigo.getPosicionEnX() + (enemigo.getVelocidad() * enemigo.getDireccion()));
        }
    }

    public boolean comienzoAtaque(){
        return false;
    }
}


