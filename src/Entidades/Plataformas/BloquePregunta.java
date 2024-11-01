package Entidades.Plataformas;


import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Plataformas.StateBloquePregunta.BloquePreguntaLleno;
import Entidades.Plataformas.StateBloquePregunta.EstadoBloquePregunta;
import Fabricas.Sprite;

import java.util.List;

public class BloquePregunta extends Plataforma {

    protected Entidad contenidoBloque;
    protected EstadoBloquePregunta estado;

    public BloquePregunta(int x, int y, Sprite sprite, Entidad p, List<Plataforma> listaPlataformaNivel) {
        super(x, y, sprite, listaPlataformaNivel);
        p.getHitbox().setBounds(p.getPosicionEnX(), p.getPosicionEnY(), 0, (int) p.getHitbox().getHeight());
        contenidoBloque = p;
        p.setPosicionEnY(-100);
        this.estado = new BloquePreguntaLleno(this);

    }

    public void interactuar(Jugador j) {
        estado.interactuar(j);
    }

    public void setEstado(EstadoBloquePregunta estado) {
        this.estado = estado;
    }

    public Entidad getContenidoBloque() {
        return contenidoBloque;
    }
}
