package Logica;

import Entidades.Enemigos.Enemigo;
import Entidades.Entidad;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Plataformas.Plataforma;
import Entidades.Jugador;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Vacio;
import EstadoMovimiento.MarioEnAire;
import Fabricas.Sprite;

import java.util.List;

public class ControladorColisiones {
    protected Nivel nivelActual;

    public ControladorColisiones(Nivel nivelActual) {
        this.nivelActual = nivelActual;
    }

    public void colisionMarioConPlataforma(List<Plataforma> listaPlataformas, Jugador mario) {
        for (Plataforma plataforma : listaPlataformas) {
            if (mario.detectarColision((plataforma))) {
                int PosicionReemplazarX = plataforma.getPosicionEnX();
                int PosicionReemplazarY = plataforma.getPosicionEnY();
                plataforma.accept(mario.getVisitorJugador());
                plataforma.actualizarEntidad();
                if (plataforma.aEliminar()) {
                    nivelActual.getVacios().add(new Vacio(PosicionReemplazarX, PosicionReemplazarY, new Sprite("", 32, 32)));
                    nivelActual.getEntidadesAEliminar().add(plataforma);
                }
            }
        }
    }

    public void colisionMarioConEnemigos(List<Enemigo> listaEnemigos, Jugador mario) {
        for (Enemigo enemigo : listaEnemigos) {
            if (mario.detectarColision(enemigo)) {
                mario.accept(enemigo.getVisitorEnemigo());
                enemigo.actualizarEntidad();
            }
        }
    }

    public void colisionMarioConMonedas(List<Moneda> listaMonedas, Jugador mario) {
        for (Moneda moneda : listaMonedas) {
            if (mario.detectarColision(moneda)) {
                moneda.accept(mario.getVisitorJugador());
                moneda.actualizarEntidad();
            }
        }
    }

    public void ColisionConProyectiles(List<Proyectil> listaProyectiles, Jugador mario) {
        for (Proyectil proyectil : listaProyectiles) {
            if (mario.detectarColision((proyectil))) {
                proyectil.accept(mario.getVisitorJugador());
                proyectil.actualizarEntidad();
            }
        }
    }

    public void colisionMarioConPowerUps(List<PowerUp> listaPowerUps, Jugador mario) {
        for (PowerUp powerUp : listaPowerUps) {
            if (mario.detectarColision((powerUp))) {
                powerUp.accept(mario.getVisitorJugador());
                powerUp.actualizarEntidad();
                mario.getEstadoJugador().actualizarSprite();
                nivelActual.getEntidadesAEliminar().add(powerUp);
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
        int toleranciaX = 5;
        if ((mario.getPosicionEnX() >= vacio.getPosicionEnX() - toleranciaX) && mario.getPosicionEnX() + mario.getHitbox().getWidth() <= vacio.getPosicionEnX() + vacio.getHitbox().getWidth() + toleranciaX) {
            if ((mario.getHitbox().getMaxY() == vacio.getPosicionEnY())) {
                Colisiono = true;
            }
        }
        return Colisiono;
    }

    public void colisionProyectilConEnemigo(List<Proyectil> listaProyectiles, Enemigo enemigo) {
        for (Proyectil proyectil : listaProyectiles) {
            proyectil.actualizarEntidad();
            int tolerancia = 5;
            if (proyectil.getPosicionEnX() >= enemigo.getPosicionEnX() - tolerancia && proyectil.getPosicionEnX() <= enemigo.getPosicionEnX() + tolerancia &&
                    proyectil.getPosicionEnY() >= enemigo.getPosicionEnY() - tolerancia && proyectil.getPosicionEnY() <= enemigo.getPosicionEnY() + tolerancia) {
                proyectil.accept(enemigo.getVisitorEnemigo());
                enemigo.actualizarEntidad();
                proyectil.actualizarEntidad();
                nivelActual.getEnemigos().remove(enemigo);
                //nivelActual.getEntidadesAEliminar().addLast(enemigo);
            }
        }
    }

    public void colisionEnemigoConPlataforma(List<Plataforma> listaPlataformas, Enemigo enemigo) {
        for (Plataforma plataforma : listaPlataformas) {
            if ((enemigo.colisionIzquierda(plataforma) || enemigo.colisionDerecha(plataforma))) {
                    enemigo.getVisitorEnemigo().visit(plataforma);
            }
        }
    }
}
