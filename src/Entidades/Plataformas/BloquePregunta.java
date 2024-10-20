package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Plataformas.StateBloquePregunta.BloquePreguntaLleno;
import Entidades.Plataformas.StateBloquePregunta.EstadoBloquePregunta;
import Fabricas.FabricaSprites;
import Fabricas.Sprite;
import Visitor.VisitorPlataforma;
import Visitor.Visitor;

public class BloquePregunta extends Plataforma{

    protected int recompensa;
    protected EstadoBloquePregunta estado;
    protected VisitorPlataforma visitor;
    protected boolean meRompi=false;

    public BloquePregunta(int x, int y, Sprite sprite, int recompensa) {
        super(x, y, sprite);
        this.estado = new BloquePreguntaLleno(this);
        this.recompensa = recompensa;
        visitor = new VisitorPlataforma(this);
    }

    public int getRecompensa() {
        return recompensa;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void interactuar(Jugador j){
        estado.interactuar(j);
    }

    public void setEstado(EstadoBloquePregunta estado) {
        this.estado = estado;
    }

    public boolean Roto(){
        return meRompi;
    }
}
