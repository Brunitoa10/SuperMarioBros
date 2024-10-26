package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Logica.Temporizador;
import Visitor.Visitor;
import Visitor.VisitorEnemigo;

import java.util.List;

public class Goomba extends Enemigo {

    protected VisitorEnemigo visitor;
    protected boolean Mori;
    protected Temporizador temporizadorGoomba;

    public Goomba(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        visitor = new VisitorEnemigo(this);
        velocidad = 1;
        temporizadorGoomba = new Temporizador();
    }

    @Override
    public void actualizar() {
        super.actualizar();
        if (temporizadorGoomba.hanPasadoNSegundos(500)) {
            this.setAEliminar();
        }
    }

    public int accept(Visitor v) {
        return v.visit(this);
    }


    public int interactuar(Jugador mario) {
        int toReturn = 0;
            if (!Mori)
                if (mario.colisionAbajo(this)) {
                    temporizadorGoomba.iniciar();
                    this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Goomba/GoombaMuerto.png");
                    this.setPosicionEnY(436);
                    Mori = true;
                    toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO;
                } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
                        mario.getEstadoJugador().recibeDanio(this);
                        toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_MUERTE_MARIO;
                        if(this.aEliminar()) {
                            Mori = true;
                            toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO;
                        }
                    }
        return toReturn;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int puntajeGoombaDestruido = 0;
        if (proyectil.getDireccion() != 0) {
            this.setAEliminar();
            puntajeGoombaDestruido = ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO;
            proyectil.setDireccion(0);
        }
        return puntajeGoombaDestruido;
    }

}
