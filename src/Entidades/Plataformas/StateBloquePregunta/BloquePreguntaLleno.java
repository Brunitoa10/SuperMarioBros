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

        PowerUp powerUp=bloquePregunta.getPowerUp();
        powerUp.getHitbox().setBounds(powerUp.getPosicionEnX(),powerUp.getPosicionEnY(),32,32);
        powerUp.getSprite().setRutaImagen(bloquePregunta.getRutaImagen()+".gif");
        System.out.println(powerUp.getSprite().getRutaImagen());
        powerUp.actualizarEntidad();
        bloquePregunta.setEstado(new BloquePreguntaVacio(bloquePregunta));
    }
}
