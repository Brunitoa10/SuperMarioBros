package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;
import EstadoJugador.SuperMario;

public class VisitorPowerUp implements Visitor {

    protected PowerUp powerUp;

    public VisitorPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    @Override
    public void visit(Jugador j) {
        if (j.getEstadoJugador().elHongoLoHaceSuperMario()) {
            powerUp.setEstadoMario(j);
            powerUp.setPosicionEnY(-100);
            powerUp.getSprite().setPosicionY(-100);
        }
        else if (j.getEstadoJugador().puedeSerMarioFuego()) {
            powerUp.setEstadoMario(j);
            powerUp.setPosicionEnY(-100);
            powerUp.getSprite().setPosicionY(-100);
        }
        else{
            j.setPuntaje(j.getPuntaje() + powerUp.getPuntaje());
            powerUp.setPosicionEnY(-100);
            powerUp.getSprite().setPosicionY(-100);
        }


    }

    @Override
    public void visit(Enemigo e) {

    }

    @Override
    public void visit(Moneda p) {

    }

    @Override
    public void visit(PowerUp m) {

    }

    public void visit(Plataforma p){

    }

    public void visit(Proyectil proyectil){

    }
}
