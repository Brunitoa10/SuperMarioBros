package Entidades.Plataformas;


import Entidades.Jugador;
import Entidades.Plataformas.StateBloquePregunta.BloquePreguntaLleno;
import Entidades.Plataformas.StateBloquePregunta.EstadoBloquePregunta;
import Entidades.Power_Ups.PowerUp;
import Fabricas.Sprite;

import java.util.List;

public class BloquePregunta extends Plataforma {

    protected PowerUp powerUp;
    protected EstadoBloquePregunta estado;
    protected boolean meRompi = false;
    protected String nombre;

    public BloquePregunta(int x, int y, Sprite sprite, PowerUp p, List<Plataforma> listaPlataformaNivel) {
        super(x, y, sprite, listaPlataformaNivel);
        p.getHitbox().setBounds(p.getPosicionEnX(), p.getPosicionEnY(), 0, (int) p.getHitbox().getHeight());
        powerUp = p;
        nombre = p.getSprite().getRutaImagen();
        p.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueNada.png");
        this.estado = new BloquePreguntaLleno(this);

    }

    public void interactuar(Jugador j) {
        estado.interactuar(j);
    }

    public void setEstado(EstadoBloquePregunta estado) {
        this.estado = estado;
    }

    public boolean Roto() {
        return meRompi;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public String getRutaImagen() {
        return nombre;
    }
}
