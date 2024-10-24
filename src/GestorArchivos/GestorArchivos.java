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

public class GestorArchivos {
	protected String rutaArchivo;

	public GestorArchivos(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
		crearArchivoSiNoExiste();
	}

	public ArrayList<JugadorRanking> cargarRanking() {
		ArrayList<JugadorRanking> jugadores = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
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

		return jugadores;
	}

	public void guardarRanking(ArrayList<JugadorRanking> jugadores) {
		try (BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(rutaArchivo), StandardCharsets.UTF_8))) {
			for (JugadorRanking jugador : jugadores) {
				bw.write(jugador.getNombre() + "," + jugador.getPuntaje());
				bw.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al guardar el archivo: " + e.getMessage());
		}
	}

	private void crearArchivoSiNoExiste() {
		File archivo = new File(rutaArchivo);
		try {
			if (archivo.createNewFile()) {
				System.out.println("Archivo de ranking creado: " + archivo.getName());
			}
		} catch (IOException e) {
			System.err.println("Error al crear el archivo: " + e.getMessage());
		}
	}
}
