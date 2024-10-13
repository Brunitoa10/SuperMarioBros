package Entidades.Plataformas;

import Entidades.Colisionable;
import Entidades.Entidad;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Plataformas.StateBloquePregunta.BloquePreguntaLleno;
import Entidades.Plataformas.StateBloquePregunta.EstadoBloquePregunta;
import Entidades.Power_Ups.PowerUp;
import Fabricas.Sprite;
import Visitor.Visitor;

public class BloquePregunta extends Plataforma{

    protected int recompensa;
    protected EstadoBloquePregunta estado;


    public BloquePregunta(int x, int y, Sprite sprite, int recompensa) {
        super(x, y, sprite);
        this.estado = new BloquePreguntaLleno();
        this.recompensa = recompensa;
    }

    public boolean detectColision(Entidad c) {
        boolean colisionan =c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
