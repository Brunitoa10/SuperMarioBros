package Entidades.Proyectiles;

import Entidades.Jugador;
import Fabricas.Sprite;
import Logica.Temporizador;
import Visitor.Visitor;
import Visitor.VisitorProyectil;

import java.util.List;

public class ProyectilKoopa extends Proyectil {
protected int direccionLocal;
protected int velocidadX;
protected int cantidadDeRebotes;
protected int colisiones=0;
protected Temporizador temporizador;

protected VisitorProyectil visitor;
    public ProyectilKoopa(int x, int y, Sprite sprite, List<Proyectil> listaProyectilNivel) {
        super(x, y, sprite, listaProyectilNivel);
        visitor = new VisitorProyectil(this);
        direccionLocal=0;
        cantidadDeRebotes=0;
        temporizador = new Temporizador();

    }

    @Override
        public int accept(Visitor v) {
            v.visit(this);
            direccion=0;
            velocidadX=5;
        return 0;
    }


    @Override
    public void actualizarEntidad() {
        super.actualizarEntidad();
        if (direccionLocal != 0 && cantidadDeRebotes < 3) {
            // Actualiza la posición en X usando velocidadX y la dirección
            this.setPosicionEnX(this.getPosicionEnX() + velocidadX * direccionLocal);
            // Asegura que el sprite también actualice su posición X
            this.getSprite().setPosicionX(this.getPosicionEnX());
        }
        if(cantidadDeRebotes==3 && temporizador.hanPasadoNSegundos(2000))
            this.setAEliminar();
    }

    public int getDireccion() {
        return direccion;
    }


        public void setDireccion(int direccion) {
        if(cantidadDeRebotes==3){
            temporizador.iniciar();;
            velocidadX=0;
            this.setPosicionEnY(this.getPosicionEnY()-12);
            if(direccionLocal==1)
                this.setPosicionEnX(this.getPosicionEnX()+12);
            if(direccionLocal==-1)
                this.setPosicionEnX(this.getPosicionEnX()-12);
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/AnimacionProyectil/AnimacionDesaparecer/ProyectilDesaparecer1.gif");
        }
        if(direccion!=0)
            this.direccion = direccion;
            direccionLocal=direccion;
            cantidadDeRebotes++;
        }

    public VisitorProyectil getVisitor() {
            return visitor;
        }


    public void Interactuar(Jugador j){
        colisiones++;
        if(cantidadDeRebotes<3) {
            if (this.colisionArriba(j) && direccionLocal == 0) {
                this.setAEliminar();
                this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueNada.png");

            }
            if (direccionLocal == 0) {
                if (this.colisionIzquierda(j)) {
                    this.setDireccion(1);
                    this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/AnimacionProyectil/ProyectilKoopa.gif");

                }
                if (this.colisionDerecha(j)) {
                    this.setDireccion(-1);
                    this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/AnimacionProyectil/ProyectilKoopa.gif");

                }
            } else {
                if (this.colisionIzquierda(j) && colisiones >= 3) {
                    j.getEstadoJugador().recibeDanio();
                }
                if (this.colisionDerecha(j) && colisiones >= 3) {
                    j.getEstadoJugador().recibeDanio();
                }
            }
        }
    }

        public void setGravedad(){

        }
}



