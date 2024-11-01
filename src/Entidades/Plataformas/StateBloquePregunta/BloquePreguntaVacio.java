package Entidades.Plataformas.StateBloquePregunta;

import Entidades.Jugador;
import Entidades.Plataformas.BloquePregunta;

public class BloquePreguntaVacio implements EstadoBloquePregunta {
    protected BloquePregunta bloquePregunta;

    public BloquePreguntaVacio(BloquePregunta bloquePregunta) {
        bloquePregunta.getSprite().setRutaImagen(bloquePregunta.getSprite().getRutaModo() + "/Bloques/BloqueDePreguntaVacio.png");
        this.bloquePregunta = bloquePregunta;
    }

    public void interactuar(Jugador j) {

    }
}
