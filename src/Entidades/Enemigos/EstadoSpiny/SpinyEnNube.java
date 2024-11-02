package Entidades.Enemigos.EstadoSpiny;

import Entidades.Enemigos.Lakitu;
import Entidades.Enemigos.Spiny;

public class SpinyEnNube implements EstadoSpiny{ //ESTADO INALCANZABLE NORMALMENTE(SOLO SE TIENE PARA SPAWN DESDE LAKITU)
    private final Lakitu lakitu;
    protected Spiny spiny;
    public SpinyEnNube(Spiny spiny, Lakitu lakitu) {
        this.lakitu = lakitu;
        this.spiny = spiny;
        spiny.getSprite().setRutaImagen(spiny.getSprite().getRutaModo()+"/Bloques/BloqueNada.png");
        spiny.getHitbox().setBounds(spiny.getPosicionEnX(),spiny.getPosicionEnY(),0,0);
    }
    @Override
    public void actualizarEstadoSpiny() {
        spiny.setPosicionEnX(lakitu.getPosicionEnX());
        spiny.setPosicionEnY(lakitu.getPosicionEnY()+74);
        }
    }

