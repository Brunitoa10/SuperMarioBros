package Entidades.Proyectiles;

import Entidades.Jugador;
import Fabricas.Sprite;
import Logica.Temporizador;
import Visitor.Visitor;

import java.util.List;

public class ProyectilKoopa extends Proyectil {
    protected int direccionLocal;
    protected int velocidadX;
    protected int cantidadDeRebotes;
    protected Temporizador temporizador;
    protected boolean gravedad = false;
    protected int contadorColisiones;

    public ProyectilKoopa(int x, int y, Sprite sprite, List<Proyectil> listaProyectilNivel) {
        super(x, y, sprite, listaProyectilNivel);
        direccionLocal = 0;
        cantidadDeRebotes = 0;
        contadorColisiones = 0;
        temporizador = new Temporizador();

    }

    @Override
    public int accept(Visitor v) {
        direccion = 0;
        velocidadX = 7;
        return v.visit(this);
    }


    @Override
    public void actualizarEntidad() {
        super.actualizarEntidad();
        if (gravedad) {
            this.setPosicionEnY(this.getPosicionEnY() + 2);
            this.getSprite().setPosicionY(this.getPosicionEnY());
            velocidadX = 0;
        } else {
            if (direccionLocal != 0 && cantidadDeRebotes < 3) {
                // Actualiza la posición en X usando velocidadX y la dirección
                this.setPosicionEnX(this.getPosicionEnX() + velocidadX * direccionLocal);
                // Asegura que el sprite también actualice su posición X
                this.getSprite().setPosicionX(this.getPosicionEnX());
            }
            if (cantidadDeRebotes == 3 && temporizador.hanPasadoNSegundos(2000))
                this.setAEliminar();
        }
    }

    public int getDireccion() {
        return direccionLocal;
    }


    public void setDireccion(int direccion) {
        if (direccion != 0) {
            this.direccion = direccion;
            direccionLocal = direccion;

            if (cantidadDeRebotes == 3) {
                temporizador.iniciar();
                velocidadX = 0;
                this.setPosicionEnY(this.getPosicionEnY() - 12);
                if (direccionLocal == 1)
                    this.setPosicionEnX(this.getPosicionEnX() + 12);
                if (direccionLocal == -1)
                    this.setPosicionEnX(this.getPosicionEnX() - 12);
                this.getSprite().setRutaImagen(this.getSprite().getRutaModo() + "/Enemigos/KoopaTroopa/AnimacionProyectil/AnimacionDesaparecer/ProyectilDesaparecer1.gif");
            }
            cantidadDeRebotes++;
        }
    }

    public void Interactuar(Jugador j) {
        contadorColisiones++;
        if (cantidadDeRebotes < 3) {
            if (this.colisionArriba(j) && direccionLocal == 0) {
                this.setAEliminar();
                this.getSprite().setRutaImagen(this.getSprite().getRutaModo() + "/Bloques/BloqueNada.png");

            }
            if (direccionLocal == 0) {
                if (this.colisionIzquierda(j)) {
                    super.setDireccion(1);
                    direccionLocal = 1;
                    this.getSprite().setRutaImagen(this.getSprite().getRutaModo() + "/Enemigos/KoopaTroopa/AnimacionProyectil/ProyectilKoopa.gif");

                }
                if (this.colisionDerecha(j)) {
                    super.setDireccion(-1);
                    direccionLocal = -1;
                    this.getSprite().setRutaImagen(this.getSprite().getRutaModo() + "/Enemigos/KoopaTroopa/AnimacionProyectil/ProyectilKoopa.gif");

                }
            } else {
                if (this.colisionIzquierda(j) && contadorColisiones > 6) {
                    j.getEstadoJugador().recibeDanio(this);
                }
                if (this.colisionDerecha(j) && contadorColisiones > 6) {
                    j.getEstadoJugador().recibeDanio(this);
                }
            }
        }
    }

    public void activarGravedad() {
        gravedad = true;
        this.velocidad=0;
    }

    public boolean puedeRomperBloques() {
        return false;
    }

}



