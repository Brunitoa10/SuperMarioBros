package EstadoJugador;

public class Puntajes {
    protected int[][] putajes;

    public Puntajes() {
        // Inicialización de la matriz con los puntajes definidos para cada estado y tipo de power-up
        this.putajes = new int[][] {
                {10, 5, 20, 100},   // Estado NORMAL
                {50, 30, 30, 100},  // Estado SUPER_MARIO
                {10, 50, 50, 100},  // Estado FLOR_DE_FUEGO
                {10, 30, 35, 100}   // Estado ESTRELLA
        };
    }

    // Método para obtener el puntaje correspondiente según el estado y tipo de power-up
    public int getPuntaje(int estado, int tipoPowerUp) {
        return putajes[estado][tipoPowerUp];
    }
}
