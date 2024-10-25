package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;

public interface Visitor {
    public int visit(Jugador j);
    public int visit(Enemigo e);
    public void visit(Moneda p);
    public int visit(PowerUp m);
    public void visit(Plataforma p);
    public int visit(Proyectil proyectil);
}
