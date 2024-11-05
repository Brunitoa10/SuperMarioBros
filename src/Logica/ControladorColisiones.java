package Logica;

import Constantes.ConstantesPuntaje;
import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.EntidadMovil;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Vacio;
import EstadoMovimiento.MarioEnAire;
import Fabricas.Sprite;
import Vista.ObserverGrafica.Observer;

import java.util.List;

public class ControladorColisiones {
    protected Nivel nivelActual;
    protected Juego juegoActual;

    public ControladorColisiones(Nivel nivelActual, Juego juego) {
        this.nivelActual = nivelActual;
        this.juegoActual = juego;
    }

    public void colisionesRestoEntidades() {
        colisionEnemigoConPlataforma(nivelActual.getPlataformas(), nivelActual.getEnemigos());
        colisionEnemigoConVacio(nivelActual.getVacios(), nivelActual.getEnemigos());
        colisionProyectilPlataforma(nivelActual.getProyectiles(), nivelActual.getPlataformas());
        colisionEnemigoConProyectiles(nivelActual.getProyectiles(), nivelActual.getEnemigos());
        colisionProyectilVacio(nivelActual.getProyectiles(), nivelActual.getVacios());
    }

    public boolean colisionesMario() {
        boolean toRet = false;
        colisionMarioConPlataforma(nivelActual.getPlataformas(), nivelActual.getJugador());
        colisionMarioConEnemigos(nivelActual.getEnemigos(), nivelActual.getJugador());
        colisionMarioConMonedas(nivelActual.getMonedas(), nivelActual.getJugador());
        colisionMarioConProyectiles(nivelActual.getProyectiles(), nivelActual.getJugador());
        toRet = colisionMarioConPowerUps(nivelActual.getPowerUps(), nivelActual.getJugador());
        colisionMarioConVacio(nivelActual.getVacios(), nivelActual.getJugador());
        return toRet;
    }

    public void colisionMarioPlataformaTickFrenado(List<Plataforma> listaPlataformas, Jugador mario){
        for (Plataforma plataforma : listaPlataformas) {
            if (mario.detectarColision((plataforma))) {
                int PosicionReemplazarX = plataforma.getPosicionEnX();
                int PosicionReemplazarY = plataforma.getPosicionEnY();
                plataforma.accept(mario.getVisitorJugador());
                if (plataforma.aEliminar()) {
                    Vacio vacio = new Vacio(PosicionReemplazarX, PosicionReemplazarY, new Sprite(plataforma.getSprite().getRutaImagen(), 32, 32, plataforma.getSprite().getRutaModo()), nivelActual.getVacios());
                    Observer observer = juegoActual.getControladorVistaJuego().registrarEntidad(vacio);
                    vacio.registrarObserver(observer);
                    nivelActual.agregarVacio(vacio);
                    vacio.setAnimacionFinal(900);
                    nivelActual.getEntidadesAEliminar().add(plataforma);
                }
            }
        }
    }

    public void colisionMarioConPlataforma(List<Plataforma> listaPlataformas, Jugador mario) {
        for (Plataforma plataforma : listaPlataformas) {
            if (mario.detectarColision((plataforma))) {
                int PosicionReemplazarX = plataforma.getPosicionEnX();
                int PosicionReemplazarY = plataforma.getPosicionEnY();
                plataforma.accept(mario.getVisitorJugador());
                plataforma.actualizarEntidad();
                if (plataforma.aEliminar()) {
                    Vacio vacio = new Vacio(PosicionReemplazarX, PosicionReemplazarY, new Sprite(plataforma.getSprite().getRutaImagen(), 32, 32, plataforma.getSprite().getRutaModo()), nivelActual.getVacios());
                    Observer observer = juegoActual.getControladorVistaJuego().registrarEntidad(vacio);
                    vacio.registrarObserver(observer);
                    nivelActual.agregarVacio(vacio);
                    vacio.setAnimacionFinal(900);
                    nivelActual.getEntidadesAEliminar().add(plataforma);
                }
            }
        }
    }

    public void colisionMarioConEnemigos(List<Enemigo> listaEnemigos, Jugador mario) {
        for (Enemigo enemigo : listaEnemigos) {
            if (mario.detectarColision(enemigo)) {
                juegoActual.sumarPuntaje(mario.accept(enemigo.getVisitorEnemigo()));
                }
            if (enemigo.aEliminar()) {
                nivelActual.getEntidadesAEliminar().add(enemigo);
            }
        }
    }

    public void colisionMarioConMonedas(List<Moneda> listaMonedas, Jugador mario) {
         for (Moneda moneda : listaMonedas) {
            if (mario.detectarColision(moneda) && !moneda.aEliminar()) {
                moneda.accept(mario.getVisitorJugador());
                juegoActual.sumarPuntaje(ConstantesPuntaje.PUNTAJE_MONEDA);
                juegoActual.agregarMoneda();
                nivelActual.getEntidadesAEliminar().add(moneda);

            }
        }
    }

    public void colisionMarioConProyectiles(List<Proyectil> listaProyectiles, Jugador mario) {
        for (Proyectil proyectil : listaProyectiles) {
            if (mario.detectarColision((proyectil))) {
                proyectil.accept(mario.getVisitorJugador());
                if (proyectil.aEliminar()) {
                    nivelActual.getEntidadesAEliminar().add(proyectil);
                }
            }
        }
    }

    public boolean colisionMarioConPowerUps(List<PowerUp> listaPowerUps, Jugador mario) {
        boolean toRet = false;
        for (PowerUp powerUp : listaPowerUps) {
            if (mario.detectarColision(powerUp) && !powerUp.aEliminar()) {
                juegoActual.sumarPuntaje(powerUp.accept(mario.getVisitorJugador()));
                mario.getEstadoJugador().actualizarEstadoJugador();
                juegoActual.reproducirSonidoPowerUp();
                powerUp.setAEliminar();
                nivelActual.getEntidadesAEliminar().add(powerUp);
            }
        }
        return toRet;
    }

    public void colisionMarioConVacio(List<Vacio> listaVacios, Jugador mario) {
        for (Vacio vacio : listaVacios) {
            vacio.actualizar();
            if (VacioColisionoAbajo(vacio, mario)) {
                if (mario.estaEnPlataforma()) {
                    mario.setEstadoMovimiento(new MarioEnAire(mario, 0));
                    mario.setEnPlataforma(false);
                }
            }
        }
    }

    private boolean VacioColisionoAbajo(Vacio vacio, EntidadMovil entidad) {
        boolean Colisiono = false;
        int toleranciaX = 5;
        if ((entidad.getPosicionEnX() >= vacio.getPosicionEnX() - toleranciaX) && entidad.getPosicionEnX() + entidad.getHitbox().getWidth() <= vacio.getPosicionEnX() + vacio.getHitbox().getWidth() + toleranciaX) {
            if ((entidad.getHitbox().getMaxY() == vacio.getPosicionEnY())) {
                Colisiono = true;
            }
        }
        return Colisiono;
    }

//METODOS PARA HILORESTOENTIDADES

    public void colisionEnemigoConPlataforma(List<Plataforma> listaPlataformas, List<Enemigo> listaEnemigos) {
        for (Enemigo enemigo : listaEnemigos) {
            if(juegoActual.marioEstaCerca(nivelActual.getJugador(), enemigo))
                enemigo.actualizar();
            for (Plataforma plataforma : listaPlataformas) {
                if (enemigo.detectarColision(plataforma)) {
                    enemigo.getVisitorEnemigo().visit(plataforma);
                }
            }
        }
    }

    public void colisionEnemigoConVacio(List<Vacio> listaVacios, List<Enemigo> listaEnemigos) {
        for (Enemigo enemigo : listaEnemigos) {
            for (Vacio vacio: listaVacios) {
                if (enemigo.colisionVacio(vacio)) {
                    enemigo.setEnPlataforma(false);
                }
            }
        }
    }

    public void colisionEnemigoConProyectiles(List<Proyectil> listaProyectiles, List<Enemigo> listaEnemigos) {
        for (Proyectil proyectil : listaProyectiles) {
            for (Enemigo enemigo : listaEnemigos) {
                if (proyectil.detectarColision(enemigo)) {
                    juegoActual.sumarPuntaje(proyectil.accept(enemigo.getVisitorEnemigo()));
                    if (enemigo.aEliminar()) {
                        nivelActual.getEntidadesAEliminar().add(enemigo);
                    }
                }
            }
            if (proyectil.aEliminar())
                nivelActual.getEntidadesAEliminar().add(proyectil);
        }
    }

    public void colisionProyectilPlataforma(List<Proyectil> listaProyectils, List<Plataforma> listaPlataformas) {
        for (Proyectil proyectil : listaProyectils) {
            for (Plataforma plataforma : listaPlataformas) {
                if (proyectil.detectarColision(plataforma)) {
                    int PosicionReemplazarX = plataforma.getPosicionEnX();
                    int PosicionReemplazarY = plataforma.getPosicionEnY();
                    proyectil.getVisitor().visit(plataforma);
                    if (plataforma.aEliminar()) {
                        Vacio vacio = new Vacio(PosicionReemplazarX, PosicionReemplazarY, new Sprite(plataforma.getSprite().getRutaImagen(), 32, 32, plataforma.getSprite().getRutaModo()), nivelActual.getVacios());
                        Observer observer = juegoActual.getControladorVistaJuego().registrarEntidad(vacio);
                        vacio.registrarObserver(observer);
                        nivelActual.agregarVacio(vacio);
                        vacio.setAnimacionFinal(2200);
                        nivelActual.getEntidadesAEliminar().add(plataforma);
                    }
                }
            }
            proyectil.actualizarEntidad();
        }
    }

    public void colisionProyectilVacio(List<Proyectil> listaProyectil, List<Vacio> listaVacios) {
        for (Proyectil proyectil : listaProyectil) {
            for (Vacio vacio : listaVacios) {
                int toleranciaX = 5;
                if ((proyectil.getPosicionEnX() >= vacio.getPosicionEnX() - toleranciaX) && proyectil.getPosicionEnX() <= vacio.getPosicionEnX() + toleranciaX) {
                    if ((vacio.getHitbox().getMinY() + toleranciaX) > proyectil.getHitbox().getMaxY() && (vacio.getHitbox().getMinY() - toleranciaX <= proyectil.getHitbox().getMaxY())) {
                        proyectil.getVisitor().visit(vacio);
                    }
                }
            }
        }
    }
}
