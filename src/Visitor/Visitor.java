package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;

public interface Visitor {
    public void visit(Jugador j);
    public void visit(Enemigo e);
    public int visit(Moneda p);
    public int visit(PowerUp m);
    public void visit(Plataforma p);
    public void visit(Proyectil proyectil);
}
