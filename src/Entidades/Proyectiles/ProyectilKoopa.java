package Entidades.Proyectiles;

import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorProyectil;

import java.util.List;

public class ProyectilKoopa extends Proyectil {
protected int direccion;
protected int velocidadX;
protected VisitorProyectil visitor;
    public ProyectilKoopa(int x, int y, Sprite sprite, List<Proyectil> listaProyectilNivel) {
        super(x, y, sprite, listaProyectilNivel);
        visitor = new VisitorProyectil(this);
    }

    @Override
        public void accept(Visitor v) {
            v.visit(this);
            direccion=0;
            velocidadX=3;
        }

    @Override
    public void actualizarEntidad() {
        super.actualizarEntidad();
        posicionX+=velocidadX*direccion;
        this.posicionX=posicionX;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
        this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/AnimacionProyectil/ProyectilKoopa.gif");
    }

    public VisitorProyectil getVisitor() {
            return visitor;
    }
}



