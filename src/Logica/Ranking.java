package Logica;


import java.io.*;
import java.util.ArrayList;

public class Ranking {

    ArrayList<JugadorRanking> jugadores;

    public Ranking() {
        jugadores = new ArrayList<>();
        cargarRanking();
    }

    private void cargarRanking() {
        try (BufferedReader br = new BufferedReader(new FileReader("Recursos/ranking.txt"))) {
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
    }

    public void actualizarRanking(String nombre, int puntuacion) {
        // Buscar si el jugador ya existe en el ranking
        for (JugadorRanking buscarJugador : jugadores) {
            if (buscarJugador.getNombre().equals(nombre)) {
                // Actualizar la puntuación si es mayor
                if (puntuacion > buscarJugador.getPuntaje()) {
                    buscarJugador.setPuntaje(puntuacion);
                }
                ordenarYGuardarRanking();
                return;
            }
        }

        // Si el jugador no existe, agregarlo al ranking
        jugadores.add(new JugadorRanking(nombre, puntuacion));
        ordenarYGuardarRanking();
    }

    private void ordenarYGuardarRanking() {
        jugadores.sort((j1, j2) -> Integer.compare(j2.getPuntaje(), j1.getPuntaje()));

        // Mantener solo los 5 mejores jugadores
        if (jugadores.size() > 5) {
            jugadores.subList(5, jugadores.size()).clear();
        }

        guardarRanking();
    }

    private void guardarRanking() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Recursos/ranking.txt"))) {
            for (JugadorRanking jugador : jugadores) {
                bw.write(jugador.getNombre() + "," + jugador.getPuntaje());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public void mostrarRanking() {
        System.out.println("Ranking:");
        for (int i = 0; i < jugadores.size(); i++) {
            JugadorRanking jugador = jugadores.get(i);
            System.out.println((i + 1) + ". " + jugador.getNombre() + ": " + jugador.getPuntaje());
        }
    }
}