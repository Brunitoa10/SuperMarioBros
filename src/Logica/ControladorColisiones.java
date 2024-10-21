package Logica;

import Entidades.Enemigos.Enemigo;
import Entidades.Entidad;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Plataformas.Plataforma;
import Entidades.Jugador;
import Entidades.Power_Ups.PowerUp;
import Entidades.Vacio;
import EstadoMovimiento.MarioEnAire;
import Fabricas.Sprite;

import java.util.List;

public class ControladorColisiones {
    protected Nivel nivelActual;
    protected List<Entidad> listaAEliminar;

    public ControladorColisiones(Nivel nivelActual, List<Entidad> listaEliminar) {
        this.nivelActual = nivelActual;
        this.listaAEliminar = listaEliminar;
    }

    public void colisionMarioConPlataforma(List<Plataforma> listaPlataformas, Jugador mario) {
        for (Plataforma plataforma : listaPlataformas) {
            if (mario.detectarColision((plataforma))) {
                int PosicionReemplazarX=plataforma.getPosicionEnX();
                int PosicionReemplazarY=plataforma.getPosicionEnY();
                mario.getVisitorJugador().visit(plataforma);
                plataforma.actualizarEntidad();
                if(plataforma.aEliminar()){
                    nivelActual.getVacios().add(new Vacio(PosicionReemplazarX,PosicionReemplazarY,new Sprite("",32,32)));
                    listaAEliminar.add(plataforma);
                }
            }
        }
    }

    public void colisionMarioConEnemigos(List<Enemigo> listaEnemigos, Jugador mario) {
        for (Enemigo enemigo : listaEnemigos) {
            if(mario.detectarColision(enemigo)) {
                enemigo.getVisitorEnemigo().visit(mario);
                enemigo.actualizarEntidad();
            }
        }
    }

    public void colisionMarioConMonedas(List<Moneda> listaMonedas, Jugador mario) {
        for(Moneda moneda : listaMonedas) {
            if(mario.detectarColision(moneda)) {
                mario.getVisitorJugador().visit(moneda);
                moneda.actualizarEntidad();
            }
        }
    }

    public void colisionMarioConPowerUps(List<PowerUp> listaPowerUps, Jugador mario) {
        for (PowerUp powerUp : listaPowerUps) {
            if (mario.detectarColision((powerUp))) {
                powerUp.getVisitor().visit(mario);
                powerUp.actualizarEntidad();
                mario.getEstadoJugador().actualizarSprite();
                listaAEliminar.add(powerUp);
            }
        }
    }

    public void colisionMarioConVacio(List<Vacio> listaVacios, Jugador mario) {
        for (Vacio vacio : listaVacios) {
            if (VacioColisionoAbajo(vacio, mario)) {
                if (mario.estaEnPlataforma()) {
                    mario.setEstadoMovimiento(new MarioEnAire(mario));
                    mario.setEnPlataforma(false);
                }
            }
        }
    }

    private boolean VacioColisionoAbajo(Vacio vacio, Jugador mario) {
        boolean Colisiono = false;
        int toleranciaX=5;
        if ((mario.getPosicionEnX() >= vacio.getPosicionEnX()-toleranciaX) && mario.getPosicionEnX()+mario.getHitbox().getWidth() <= vacio.getPosicionEnX()+vacio.getHitbox().getWidth()+toleranciaX ) {
            if ((mario.getHitbox().getMaxY() == vacio.getPosicionEnY())) {
                Colisiono = true;
            }
        }
        return Colisiono;
    }
}
