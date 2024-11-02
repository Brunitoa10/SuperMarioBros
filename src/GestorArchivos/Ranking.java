package GestorArchivos;


import Constantes.CadenasValidacion;

import java.util.ArrayList;
import java.util.Comparator;

    public class Ranking {
        protected ArrayList<JugadorRanking> jugadores;
        protected GestorArchivos gestorArchivos;
        protected JugadorRanking jugador;
        protected JugadorRanking jugadorMenor;

        public Ranking() {
            this.gestorArchivos = new GestorArchivos(CadenasValidacion.RUTA_ARCHIVO_RANKING);
            jugadores = gestorArchivos.cargarRanking();
            ordenarYLimitarJugadores();
        }

        public void agregarAlRanking(String nombre, int puntuacion) {
            int indiceJugador = encontrarJugadorPorNombre(nombre);

            if (indiceJugador != CadenasValidacion.FUERA_DE_RANGO) {
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
            jugador = jugadores.get(indiceJugador);
            if (puntuacion > jugador.getPuntaje()) {
                jugador.setPuntaje(puntuacion);
            }
        }

        private void agregarNuevoJugadorSiCorresponde(String nombre, int puntuacion) {
            if (jugadores.size() < CadenasValidacion.MAXIMO_JUGADORES_RANKING) {
                jugadores.add(new JugadorRanking(nombre, puntuacion));
            } else {
                reemplazarJugadorSiEsNecesario(nombre, puntuacion);
            }
        }

        private void reemplazarJugadorSiEsNecesario(String nombre, int puntuacion) {
            jugadorMenor = jugadores.get(jugadores.size() - 1);
            if (puntuacion > jugadorMenor.getPuntaje()) {
                jugadores.set(jugadores.size() - 1, new JugadorRanking(nombre, puntuacion));
            }
        }


        private void ordenarYLimitarJugadores() {
            jugadores.sort(Comparator.comparingInt(JugadorRanking::getPuntaje).reversed());
            if (jugadores.size() > CadenasValidacion.MAXIMO_JUGADORES_RANKING) {
                jugadores = new ArrayList<>(jugadores.subList(CadenasValidacion.MINIMO_JUGADORES_RANKING, CadenasValidacion.MAXIMO_JUGADORES_RANKING));
            }
        }


        private int encontrarJugadorPorNombre(String nombre) {
            int indiceJugador = CadenasValidacion.FUERA_DE_RANGO;

            for (int i = 0; i < jugadores.size(); i++) {
                if (jugadores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                    indiceJugador = i;
                }
            }

            return indiceJugador;
        }

    }