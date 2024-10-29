package Entidades.Plataformas.StateBloquePregunta;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Plataformas.BloquePregunta;

public class BloquePreguntaLleno implements EstadoBloquePregunta {
    protected BloquePregunta bloquePregunta;

    public BloquePreguntaLleno(BloquePregunta bloquePregunta) {
        this.bloquePregunta = bloquePregunta;
    }

    public void interactuar(Jugador j) {
        Entidad powerUp = bloquePregunta.getContenidoBloque();
        powerUp.getHitbox().setBounds(powerUp.getPosicionEnX(), bloquePregunta.getPosicionEnY() - 32, 32, 32);
        powerUp.setPosicionEnY(bloquePregunta.getPosicionEnY() - 32);
        System.out.println(powerUp.getSprite().getRutaImagen());
        powerUp.actualizarEntidad();
        bloquePregunta.setEstado(new BloquePreguntaVacio(bloquePregunta));
    }
}
