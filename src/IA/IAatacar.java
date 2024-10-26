package IA;

import Entidades.Enemigos.Enemigo;
import Entidades.Jugador;

public class IAatacar implements ComportamientoIA{
	protected Jugador mario;
	protected int VelocidadX;
	protected int VelocidadY;
	protected final static int POSICION_ESTANDAR=50;
	protected final static int RANGO_JUGADOR=200;
	protected boolean comienzoAtaque;
	public IAatacar(Jugador mario){
		this.mario = mario;
		VelocidadX = 3;
		VelocidadY = -4;
		comienzoAtaque = false;
	}
	@Override
	public void actualizar(Enemigo enemigo) {

		enemigo.setPosicionEnX(enemigo.getPosicionEnX()+VelocidadX);
		enemigo.setPosicionEnY(enemigo.getPosicionEnY()+VelocidadY);
		if(enemigo.getPosicionEnY()<=POSICION_ESTANDAR)
			VelocidadY=0;
		if(enemigo.getPosicionEnX()>mario.getPosicionEnX()+RANGO_JUGADOR && enemigo.getPosicionEnX()<mario.getPosicionEnX()+2*RANGO_JUGADOR)
			VelocidadX=0;
		if (mario.getPosicionEnX() >= enemigo.getPosicionEnX()-RANGO_JUGADOR/2) {
				VelocidadX = 4;
			}


		if(VelocidadX==0 && VelocidadY==0)
			comienzoAtaque = true;
	}

	@Override
	public void activarGravedad() {

	}

	public boolean comienzoAtaque(){
		return comienzoAtaque;
	}

}
