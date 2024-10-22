package Visitor;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilKoopa;
import Entidades.Vacio;
import EstadoMovimiento.MarioEnAire;
import EstadoMovimiento.MarioParado;
import Logica.ConstantesPuntaje;

public class VisitorJugador implements Visitor {

    protected Jugador mario;

    public VisitorJugador(Jugador mario) {
        this.mario = mario;
    }

    @Override
    public void visit(Jugador J) {

    }

    @Override
    public void visit(Enemigo e) {

    }

    @Override
    public int visit(PowerUp p) {
        int toReturn = 0;

        if (mario.getEstadoJugador().elHongoLoHaceSuperMario()) {
            p.setEstadoMario(mario);
            p.setPosicionEnY(-100);
            p.getSprite().setPosicionY(-100);
        }
        else if (mario.getEstadoJugador().puedeSerMarioFuego()) {
            p.setEstadoMario(mario);
            p.setPosicionEnY(-100);
            p.getSprite().setPosicionY(-100);
        }
        else{
            toReturn = p.getPuntaje();
            p.setPosicionEnY(-100);
            p.getSprite().setPosicionY(-100);
        }
        return toReturn;
    }

    @Override
    public int visit(Moneda moneda) {
        moneda.setAEliminar();
        moneda.setPosicionEnY(-100);
        return ConstantesPuntaje.PUNTAJE_MONEDA;
    }

    public void visit(Plataforma plataforma){
        if (mario.colisionDerecha(plataforma)){
            int posicionATeletransportar = (int) (plataforma.getHitbox().getMinX() - mario.getHitbox().getWidth());
            mario.setPosicionEnX(posicionATeletransportar);
        }
        else if (mario.colisionIzquierda(plataforma)){
            int posicionATeletransportar = (int) (plataforma.getHitbox().getMaxX());
            mario.setPosicionEnX(posicionATeletransportar);
        }
        else if (mario.colisionAbajo(plataforma)){
            System.out.println("estoy colisionando mi rey");
            mario.setEnPlataforma(true);
            mario.setEstadoMovimiento(new MarioParado(mario));
            mario.setPosicionEnY((int) (plataforma.getHitbox().getMinY() - mario.getHitbox().getHeight()));

        }
        else if (mario.colisionArriba(plataforma)) {
            mario.setEstadoMovimiento(new MarioEnAire(mario));
            plataforma.interactuar(mario);
            System.out.println("colision arriba!");
        }
    }

    @Override
    public void visit(Proyectil proyectil) {
        if(proyectil.colisionIzquierda(mario)){
            proyectil.setDireccion(1);
            proyectil.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/AnimacionProyectil/ProyectilKoopa.gif");
        }
        if(proyectil.colisionDerecha(mario)){
            proyectil.setDireccion(-1);
            proyectil.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/AnimacionProyectil/ProyectilKoopa.gif");
        }
    }


    public void visit(Vacio vacio) {
        int tolerancia = 5;
        if (mario.colisionAbajo(vacio)) {
            System.out.println("colision abajo!");}
        if (mario.getPosicionEnY() > vacio.getHitbox().getMinY() + tolerancia)
            System.out.println("picho");
        if (mario.colisionAbajo(vacio) && mario.getPosicionEnY() > vacio.getHitbox().getMinY() + tolerancia) {
            System.out.println("colision abajo!");
            mario.setEstadoMovimiento(new MarioEnAire(mario));

        }
    }
}




