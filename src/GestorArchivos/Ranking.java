package GestorArchivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;

import Vista.Paneles.CadenasValidacion;

public class Ranking {
	protected ArrayList<JugadorRanking> jugadores;
	protected GestorArchivos gestorArchivos;

	public Ranking() {
		this.gestorArchivos = new GestorArchivos(CadenasValidacion.RUTA_ARCHIVO_RANKING);
		jugadores = gestorArchivos.cargarRanking();
		ordenarYLimitarJugadores();
	}

	public void agregarAlRanking(String nombre, int puntuacion) {
	    int indiceJugador = encontrarJugadorPorNombre(nombre);

	    if (indiceJugador != -1) {
	        actualizarPuntajeSiEsMayor(indiceJugador, puntuacion);
	    } else {
	        agregarNuevoJugadorSiCorresponde(nombre, puntuacion);
	    }

	    ordenarYLimitarJugadores();
	    gestorArchivos.guardarRanking(jugadores);
	}

	public ArrayList<JugadorRanking> mostrarRanking() {
	    return jugadores;
	}

	private void actualizarPuntajeSiEsMayor(int indiceJugador, int puntuacion) {
	    JugadorRanking jugador = jugadores.get(indiceJugador);
	    if (puntuacion > jugador.getPuntaje()) {
	        jugador.setPuntaje(puntuacion);
	    }
	}

	private void agregarNuevoJugadorSiCorresponde(String nombre, int puntuacion) {
	    if (jugadores.size() < 5) {
	        jugadores.add(new JugadorRanking(nombre, puntuacion));
	    } else {
	        reemplazarJugadorSiEsNecesario(nombre, puntuacion);
	    }
	}

	private void reemplazarJugadorSiEsNecesario(String nombre, int puntuacion) {
	    JugadorRanking jugadorMenor = jugadores.get(jugadores.size() - 1);
	    if (puntuacion > jugadorMenor.getPuntaje()) {
	        jugadores.set(jugadores.size() - 1, new JugadorRanking(nombre, puntuacion));
	    }
	}
	

	private void ordenarYLimitarJugadores() {
	    jugadores.sort(Comparator.comparingInt(JugadorRanking::getPuntaje).reversed());
	    if (jugadores.size() > 5) {
	        jugadores = new ArrayList<>(jugadores.subList(0, 5));
	    }
	}



	private int encontrarJugadorPorNombre(String nombre) {
		int indiceJugador = -1;

		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).getNombre().equalsIgnoreCase(nombre)) {
				indiceJugador = i;
			}
		}

		return indiceJugador;
	}

}