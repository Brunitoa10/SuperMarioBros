package EstadoJugador;

import Constantes.ConstantesPuntaje;

public class Puntajes {
    protected int[][] putajes;

    public Puntajes() {
        // Inicialización de la matriz con los puntajes definidos para cada estado y tipo de power-up
        this.putajes = new int[][] {
                {ConstantesPuntaje.PUNTAJE_SUPER_CHAMPINION_NORMAL, ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_NORMAL, ConstantesPuntaje.PUNTAJE_ESTRELLA_NORMAL, ConstantesPuntaje.PUNTAJE_CHAMPINON_VERDE},   // Estado NORMAL
                {ConstantesPuntaje.PUNTAJE_SUPER_CHAMPINION_SUPER_MARIO, ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_SUPER_MARIO, ConstantesPuntaje.PUNTAJE_ESTRELLA_SUPER_MARIO, ConstantesPuntaje.PUNTAJE_CHAMPINON_VERDE},  // Estado SUPER_MARIO
                {ConstantesPuntaje.PUNTJE_SUPER_CHAMPINION_FUEGO, ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_CON_FUEGO, ConstantesPuntaje.PUNTAJE_ESTRELLA_CON_FUEGO, ConstantesPuntaje.PUNTAJE_CHAMPINON_VERDE},  // Estado FLOR_DE_FUEGO
                {ConstantesPuntaje.PUNTJE_SUPER_CHAMPINION_CON_ESTRELLA, ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_CON_ESTRELLA, ConstantesPuntaje.PUNTAJE_ESTRELLA_CON_ESTRELLA, ConstantesPuntaje.PUNTAJE_CHAMPINON_VERDE}   // Estado ESTRELLA
        };
    }

    // Método para obtener el puntaje correspondiente según el estado y tipo de power-up
    public int getPuntaje(int estado, int tipoPowerUp) {
        return putajes[estado][tipoPowerUp];
    }
}
