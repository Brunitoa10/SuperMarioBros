package Logica;

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

public class Ranking {

	protected ArrayList<JugadorRanking> jugadores;

	public Ranking() {
		jugadores = new ArrayList<>();
		crearArchivoSiNoExiste();
		cargarRanking();
	}

	private void crearArchivoSiNoExiste() {
		File archivo = new File("src/Recursos/ranking.txt");
		try {
			if (archivo.createNewFile()) {
				System.out.println("Archivo de ranking creado: " + archivo.getName());
			}
		} catch (IOException e) {
			System.err.println("Error al crear el archivo: " + e.getMessage());
		}
	}

	private void cargarRanking() {
		try (BufferedReader br = new BufferedReader(new FileReader("src/Recursos/ranking.txt"))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (!linea.trim().isEmpty()) {
					String[] partes = linea.split(",");
					if (partes.length == 2) {
						try {
							int puntaje = Integer.parseInt(partes[1].trim());
							jugadores.add(new JugadorRanking(partes[0].trim(), puntaje));
						} catch (NumberFormatException e) {
							System.err.println("Error en la línea: " + linea + " - La puntuación no es un número válido.");
						}
					} else {
						System.err.println("Error en la línea: " + linea + " - Formato incorrecto.");
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("El archivo no se encontró: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}

		jugadores.sort((j1, j2) -> Integer.compare(j2.getPuntaje(), j1.getPuntaje()));

		if (jugadores.size() > 5) {
			jugadores = new ArrayList<>(jugadores.subList(0, 5));
		}
	}

	public void agregarAlRanking(String nombre, int puntuacion) {
		int indiceJugador = encontrarJugadorPorNombre(nombre);

		if (indiceJugador != -1) {
			if (puntuacion > jugadores.get(indiceJugador).getPuntaje()) {
				jugadores.get(indiceJugador).setPuntaje(puntuacion);
			}
		} else {
			if (jugadores.size() < 5) {
				jugadores.add(new JugadorRanking(nombre, puntuacion));
			} else {
				JugadorRanking jugadorMenor = jugadores.get(jugadores.size() - 1);
				if (puntuacion > jugadorMenor.getPuntaje()) {
					jugadores.set(jugadores.size() - 1, new JugadorRanking(nombre, puntuacion));
				}
			}
		}

		jugadores.sort((j1, j2) -> Integer.compare(j2.getPuntaje(), j1.getPuntaje()));

		if (jugadores.size() > 5) {
			jugadores = new ArrayList<>(jugadores.subList(0, 5));
		}

		guardarRanking();
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

	private void guardarRanking() {
		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("src/Recursos/ranking.txt"), StandardCharsets.UTF_8))) {
			for (JugadorRanking jugador : jugadores) {
				bw.write(jugador.getNombre() + "," + jugador.getPuntaje());
				bw.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al guardar el archivo: " + e.getMessage());
		}
	}

	public ArrayList<JugadorRanking> mostrarRanking() {
		return jugadores;
	}
}