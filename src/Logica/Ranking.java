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

	    // Constructor que inicializa la lista de jugadores y carga el ranking desde el archivo
	    public Ranking() {
	        jugadores = new ArrayList<>();
	        crearArchivoSiNoExiste();
	        cargarRanking();
	    }

	    // Método para crear el archivo si no existe
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

	    // Método para cargar el ranking desde el archivo
	    private void cargarRanking() {
	        try (BufferedReader br = new BufferedReader(new FileReader("src/Recursos/ranking.txt"))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                if (!linea.trim().isEmpty()) { // Ignorar líneas vacías
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
	        
	        // Ordenar jugadores por puntaje de forma descendente
	        jugadores.sort((j1, j2) -> Integer.compare(j2.getPuntaje(), j1.getPuntaje()));
	        // Asegurar que solo haya 5 jugadores en el ranking
	        if (jugadores.size() > 5) {
	            jugadores = new ArrayList<>(jugadores.subList(0, 5));
	        }
	    }

	    // Método para agregar un jugador al ranking
	    public void agregarAlRanking(String nombre, int puntuacion) {
	        // Buscar si el jugador ya existe en el ranking
	        int indiceJugador = -1;
	        for (int i = 0; i < jugadores.size(); i++) {
	            if (jugadores.get(i).getNombre().equalsIgnoreCase(nombre)) { // Comparar ignorando mayúsculas
	                indiceJugador = i;
	                break;
	            }
	        }

	        if (indiceJugador != -1) {
	            // Si el jugador existe, actualizar su puntuación si es mayor
	            if (puntuacion > jugadores.get(indiceJugador).getPuntaje()) {
	                jugadores.get(indiceJugador).setPuntaje(puntuacion);
	            }
	        } else {
	            // Si el jugador no existe, agregarlo al ranking
	            if (jugadores.size() < 5) {
	                jugadores.add(new JugadorRanking(nombre, puntuacion));
	            } else {
	                // Si hay 5 jugadores, comparar el puntaje del nuevo jugador con el de los existentes
	                JugadorRanking jugadorMenor = jugadores.get(jugadores.size() - 1); // El último es el de menor puntaje
	                if (puntuacion > jugadorMenor.getPuntaje()) {
	                    // Reemplazar al jugador con el menor puntaje
	                    jugadores.set(jugadores.size() - 1, new JugadorRanking(nombre, puntuacion));
	                }
	            }
	        }

	        // Ordenar el ranking por puntuación de forma descendente
	        jugadores.sort((j1, j2) -> Integer.compare(j2.getPuntaje(), j1.getPuntaje()));

	        // Asegurar que solo haya 5 jugadores en el ranking
	        if (jugadores.size() > 5) {
	            jugadores = new ArrayList<>(jugadores.subList(0, 5));
	        }

	        // Guardar el ranking actualizado en el archivo
	        guardarRanking();
	    }

	    // Método para guardar el ranking en el archivo
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

	    // Método para mostrar el ranking
	    public ArrayList<JugadorRanking> mostrarRanking() {
	        return jugadores;
	    }
}