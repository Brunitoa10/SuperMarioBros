package Entidades.Plataformas;

import Entidades.EntidadInmovil.Moneda;
import Entidades.Plataformas.StateBloquePregunta.BloquePreguntaLleno;
import Entidades.Plataformas.StateBloquePregunta.EstadoBloquePregunta;
import Entidades.Power_Ups.PowerUp;
import Fabricas.Sprite;

public class BloquePregunta extends Plataforma{

    protected PowerUp powerUp;
    protected Moneda moneda;
    protected EstadoBloquePregunta estado;


    public BloquePregunta(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.estado = new BloquePreguntaLleno();
    }
}
