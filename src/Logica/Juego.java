package Logica;

import java.util.List;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.FabricaEntidad;

import Vista.Controladores.ControladorVistaJuego;
import Vista.ObserverGrafica.Observer;


public class Juego {

    protected ControladorVistaJuego controlador_vistas;
    // protected GeneradorNivel generadorNivel;
    // protected FabricaSprites fabricaSprites;
    protected FabricaEntidad fabricaEntidad;
    protected Nivel nivelActual;

    // Comunicacion con parte grafica
    public void set_controlador_vistas(ControladorVistaJuego controlador_vistas) {
        this.controlador_vistas = controlador_vistas;
    }

    public void iniciar() {
        System.out.println("Logica mostrar modo de juego");
        controlador_vistas.mostrar_pantalla_nivel();
    }

    public void reiniciar(Nivel nivel) {
        //Implemetar
    }

    public void mostrar_pantalla_ranking() {
        System.out.println("Logica mostrar ranking");
        controlador_vistas.mostrar_pantalla_ranking();
    }
    
    protected void registrar_observers() {
		registrar_observer_jugador(nivelActual.getJugador());
		registrar_observers_para_entidades(nivelActual.getEnemigos());
		registrar_observers_para_entidades(nivelActual.getPlataformas());
		registrar_observers_para_entidades(nivelActual.getPowerUps());
		registrar_observers_para_entidades(nivelActual.getProyectiles());
	}
	
	protected void registrar_observer_jugador(Jugador vehiculo_jugador) {
		Observer observer_jugador = controlador_vistas.registrar_entidad(vehiculo_jugador);
		vehiculo_jugador.registrar_observer(observer_jugador);
	}
	
	
	protected void registrar_observers_para_entidades(List<? extends Entidad> entidades) {
		for(Entidad entidad : entidades) {
			Observer observer = controlador_vistas.registrar_entidad(entidad);
			entidad.registrar_observer(observer);
		}
	}

}
