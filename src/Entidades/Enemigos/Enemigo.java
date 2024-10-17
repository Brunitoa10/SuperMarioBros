package Entidades.Enemigos;

import Entidades.EntidadMovil;
import Fabricas.Sprite;

public abstract class Enemigo extends EntidadMovil {

    private int puntajeDestruido;
    protected int puntajeKill;
    protected int posicionx;
    protected int posiciony;
    protected int direccion = 1; // Dirección inicial (1: derecha, -1: izquierda)

    public Enemigo(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        posicionx = x;
        posiciony = y;
    }

    // Método para hacer caminar al enemigo
    public void caminar() {
        // La posición en X se ajusta de acuerdo a la dirección
        posicionx += direccion * (velocidad != 0 ? velocidad : 3);  // Si tiene velocidad, la usa; si no, usa 3
        setPosicionEnX(posicionx);
    }

    // Método de actualización que puede extenderse en subclases
    public void actualizar() {
        caminar();
        super.actualizarEntidad();
        // Otros comportamientos que puedan ser agregados como colisiones o IA
    }

    // Método para cambiar la dirección del enemigo
    public void cambiarDireccion() {
        direccion = -direccion; // Invierte la dirección
    }
}
