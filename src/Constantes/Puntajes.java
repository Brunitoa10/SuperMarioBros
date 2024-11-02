package Constantes;

public class Puntajes {
    protected int[][] puntajes;

    public Puntajes() {
        // Inicialización de la matriz con los puntajes definidos para cada estado y tipo de power-up
        this.puntajes = new int[][]{
                {ConstantesPuntaje.PUNTAJE_SUPER_CHAMPINION_NORMAL, ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_NORMAL, ConstantesPuntaje.PUNTAJE_ESTRELLA_NORMAL},   // Estado NORMAL
                {ConstantesPuntaje.PUNTAJE_SUPER_CHAMPINION_SUPER_MARIO, ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_SUPER_MARIO, ConstantesPuntaje.PUNTAJE_ESTRELLA_SUPER_MARIO},  // Estado SUPER_MARIO
                {ConstantesPuntaje.PUNTJE_SUPER_CHAMPINION_FUEGO, ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_CON_FUEGO, ConstantesPuntaje.PUNTAJE_ESTRELLA_CON_FUEGO},  // Estado FLOR_DE_FUEGO
                {ConstantesPuntaje.PUNTJE_SUPER_CHAMPINION_CON_ESTRELLA, ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_CON_ESTRELLA, ConstantesPuntaje.PUNTAJE_ESTRELLA_CON_ESTRELLA}   // Estado ESTRELLA
        };
    }

    public int getPuntaje(int estado, int tipoPowerUp) {
        return puntajes[estado][tipoPowerUp];
    }
}

