package Entidades.Proyectiles;

import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorProyectil;
public class ProyectilKoopa extends Proyectil {
protected int direccionLocal;
protected int velocidadX;
protected VisitorProyectil visitor;
    public ProyectilKoopa(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        visitor = new VisitorProyectil(this);
        direccionLocal=0;
    }

    @Override
        public void accept(Visitor v) {
            v.visit(this);
            velocidadX=3;
        }

    @Override
    public void actualizarEntidad() {
        super.actualizarEntidad();
        if (direccionLocal != 0) {
            // Actualiza la posición en X usando velocidadX y la dirección
            this.setPosicionEnX(this.getPosicionEnX() + velocidadX * direccionLocal);
            // Asegura que el sprite también actualice su posición X
            this.getSprite().setPosicionX(this.getPosicionEnX());

        }
    }

    public int getDireccion() {
        return direccion;
        }

        public void setDireccion(int direccion) {
        this.direccion = direccion;
        direccionLocal=direccion;
        }

        public VisitorProyectil getVisitor() {
            return visitor;
        }


    public void Interactuar(Jugador j){
        if(this.colisionArriba(j) && direccionLocal==0) {
            this.setPosicionEnY(-100);
            this.setAEliminar();
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueNada.png");

        }
        if(direccionLocal==0) {
            if (this.colisionIzquierda(j)) {
                this.setDireccion(1);
                this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/AnimacionProyectil/ProyectilKoopa.gif");
            }
            if (this.colisionDerecha(j)) {
                this.setDireccion(-1);
                this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/AnimacionProyectil/ProyectilKoopa.gif");
            }
        }else{
            if (this.colisionIzquierda(j)) {
                j.getEstadoJugador().recibeDanio();
            }
            if (this.colisionDerecha(j)) {
                j.getEstadoJugador().recibeDanio();
            }
        }
    }

    }



