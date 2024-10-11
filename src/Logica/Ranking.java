package Logica;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Ranking {

    ArrayList<JugadorRanking> jugadores;

    public Ranking(int[] arregloRanking) {

        jugadores = crearListaJugadoresRanking();

    }

    public void actualizarRanking(String nombre, int puntuacion){

        reemplazarRanking(jugadores,  nombre, puntuacion);

    }

    public void mostrarRanking(ArrayList<JugadorRanking> jugadores){
        System.out.println("Ranking:");
        for (int i = 0; i < 5 && i < jugadores.size(); i++) {
            JugadorRanking jugador = jugadores.get(i);
            System.out.println((i + 1) + ". " + jugador.nombre + ": " + jugador.puntaje);
        }

    }

    private ArrayList<JugadorRanking> crearListaJugadoresRanking(){
        ArrayList<JugadorRanking> listaParaRetornar = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("ranking.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                listaParaRetornar.add(new JugadorRanking(partes[0], Integer.parseInt(partes[1])));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return listaParaRetornar;
    }

    private void actualizarJugadores( ArrayList<JugadorRanking> jugadores,String nombre, int puntaje ){

        jugadores.sort((j1, j2) -> Integer.compare(j2.puntaje, j1.puntaje));
        jugadores.getLast().nombre = nombre;
        jugadores.getLast().puntaje = puntaje;

    }



    private void reemplazarRanking(ArrayList<JugadorRanking> jugadores, String nombre, int puntaje){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ranking.txt"))) {
            for (JugadorRanking auxiliarParaActualizar : jugadores) {
                bw.write(auxiliarParaActualizar.nombre + "," + auxiliarParaActualizar.puntaje);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }

        actualizarJugadores(jugadores, nombre, puntaje);

    }

}
