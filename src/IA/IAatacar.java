package IA;

import Entidades.Enemigos.Enemigo;
import Entidades.Jugador;

public class IAatacar implements ComportamientoIA{
	protected Jugador mario;
	protected int VelocidadX;
	protected int VelocidadY;
	protected final static int POSICION_ESTANDAR=50;
	protected final static int RANGO_JUGADOR=200;
	public IAatacar(Jugador mario){
		this.mario = mario;
		VelocidadX = 6;
		VelocidadY = 4;
	}
	@Override
	public void actualizar(Enemigo enemigo) {
		enemigo.setPosicionEnX(enemigo.getPosicionEnX()+VelocidadX);
		enemigo.setPosicionEnY(enemigo.getPosicionEnY()+VelocidadY);
		if(enemigo.getPosicionEnY()<=POSICION_ESTANDAR)
			VelocidadY=0;
		if(enemigo.getPosicionEnX()>mario.getPosicionEnX() && enemigo.getPosicionEnX()<mario.getPosicionEnX()+RANGO_JUGADOR)
			VelocidadX=0;
		if(mario.getPosicionEnX()<enemigo.getPosicionEnX()){
			VelocidadX=-6;
		}
		if(mario.getPosicionEnX()>=enemigo.getPosicionEnX()){
			VelocidadX=6;
		}
	}

	@Override
	public void activarGravedad() {

	}

}
