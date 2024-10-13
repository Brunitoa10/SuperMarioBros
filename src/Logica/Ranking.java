package Logica;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Ranking {

    ArrayList<JugadorRanking> jugadores;

    public Ranking() {
        jugadores = new ArrayList<>();
        cargarRanking();
    }

    private void cargarRanking() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Recursos/ranking.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) { // Ignorar líneas vacías
                    String[] partes = linea.split(",");
                    if (partes.length == 2) {
                        try {
                            int puntaje = Integer.parseInt(partes[1]);
                            jugadores.add(new JugadorRanking(partes[0], puntaje));
                        } catch (NumberFormatException e) {
                            System.err.println("Error en la línea: " + linea + " - La puntuación no es un número válido.");
                        }
                    } else {
                        System.err.println("Error en la línea: " + linea + " - Formato incorrecto.");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        jugadores.sort((j1, j2) -> Integer.compare(j2.getPuntaje(), j1.getPuntaje()));
        // Asegurar que solo haya 5 jugadores en el ranking
        if (jugadores.size() > 5) {
            jugadores = new ArrayList<>(jugadores.subList(0, 5));
        }
    }

    public void agregarAlRanking(String nombre, int puntuacion) {
        // Buscar si el jugador ya existe en el ranking
        int indiceJugador = -1;
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getNombre().equals(nombre)) {
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
            jugadores.add(new JugadorRanking(nombre, puntuacion));
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

    private void guardarRanking() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Recursos/ranking.txt"), StandardCharsets.UTF_8))) {
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