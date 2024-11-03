package IA;


import Entidades.Enemigos.Enemigo;
import Logica.Temporizador;

public class IACaminar implements ComportamientoIA {
   
    protected Temporizador temporizador;

    public IACaminar() {
    }

    @Override
    public void actualizar(Enemigo enemigo) {
        if(!enemigo.estoyEnPlataforma()) {
            enemigo.setPosicionEnY(enemigo.getPosicionEnY()+2);
        }
       
        if(!enemigo.estoyEnPlataforma() && enemigo.getPosicionEnY()>520) {
            enemigo.setAEliminar();
        }
        if (enemigo.estoyEnPlataforma()) {
            enemigo.setPosicionEnX(enemigo.getPosicionEnX() + (enemigo.getVelocidad() * enemigo.getDireccion()));
                if(enemigo.getPosicionEnX() < -800) {
                    enemigo.setDireccion(1);
                }
            }
        }

    public boolean comienzoAtaque(){
        return false;
    }
}


