package Entidades.Plataformas.StateBloquePregunta;
import Entidades.Power_Ups.PowerUp;
import Fabricas.CreadorEntidad;
import Entidades.Jugador;
import Entidades.Plataformas.BloquePregunta;
import Entidades.Plataformas.StateBloquePregunta.EstadoBloquePregunta;
import Fabricas.FabricaEntidad;


public class BloquePreguntaLleno implements EstadoBloquePregunta {
    protected BloquePregunta bloquePregunta;
    public BloquePreguntaLleno(BloquePregunta bloquePregunta) {
            this.bloquePregunta=bloquePregunta;
    }

    public void interactuar(Jugador j) {
        bloquePregunta.setEstado(new BloquePreguntaVacio(bloquePregunta));

    }
}
