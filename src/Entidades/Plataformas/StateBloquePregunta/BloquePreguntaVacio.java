package Entidades.Plataformas.StateBloquePregunta;

import Entidades.Jugador;
import Entidades.Plataformas.BloquePregunta;

public class BloquePreguntaVacio implements EstadoBloquePregunta {
    protected BloquePregunta bloquePregunta;
    public BloquePreguntaVacio(BloquePregunta bloquePregunta) {
        bloquePregunta.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueDePreguntaVacio.png");
        this.bloquePregunta=bloquePregunta;
    }

    public void Interactuar(Jugador j) {

    }
}
