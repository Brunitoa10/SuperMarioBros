package Entidades;

import Fabricas.Sprite;
import Vista.ObserverGrafica.Observer;

public abstract class Entidad implements EntidadLogica,Colisionable {
    protected int posicionX;
    protected int posicionY;
    protected int ancho;
    protected int alto;
    protected Sprite sprite;
    protected Observer observer;

    public Entidad(int x, int y, Sprite sprite) {
        this.posicionX = x;
        this.posicionY = y;
        this.sprite = sprite;
    }

    @Override
    public Sprite get_sprite() {
        return sprite;
    }

    @Override
    public int get_posicion_x() {
        return posicionX;
    }

    public void set_posicion_x(int x) {
        posicionX = x;
    }

    @Override
    public int get_posicion_y() {
        return posicionY;
    }

    public void set_posicion_y(int y) {
        posicionY = y;
    }

    public void registrar_observer(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void actualizar_entidad() {

    }

    public boolean detectColission(Entidad e){
        boolean detectado = false;
        if(posicionX<e.get_posicion_x()){//Si la entidad receptora se encuentra a la izquierda de la del parametro
            if(posicionX+ancho/2>=e.get_posicion_x()-e.get_sprite().get_ancho()/2){//chequeo si llegan a chocarse la parte derecha de la receptora con la parte izquierda con la de del parametro
                if(posicionY>=e.get_posicion_y()){//Si la entidad 1 esta arriba de la entidad 2 chequeo que se toquen con la posicion Y sin contemplar la altura y Entidad 2 contemplandola
                    if(posicionY<=e.get_posicion_y()+e.get_sprite().get_alto()){//Chequeo si la parte de abajo de entidad 1 esta chocando con la parte mas alta de entidad 2
                        detectado = true;
                    }
                }else{//La entidad 1 esta por debajo de la entidad 2
                    if(posicionY+alto<=e.get_posicion_y()){//Chequeo si la parte de arriba de entidad 1 esta chocando con la parte mas baja
                        detectado = true;
                    }
                }
            }else{//Si la entidad receptora se encuentra a la derecha de la que entra por parametro
                    if(posicionX-ancho/2<=e.get_posicion_x()+e.get_sprite().get_ancho()/2){//chequeo si llegan a chocarse la parte izquierda de la receptora con la parte derecha con la de del parametro
                        if(posicionY<=e.get_posicion_y()+e.get_sprite().get_alto()){//Chequeo si la parte de abajo de entidad 1 esta chocando con la parte mas alta de entidad 2
                            detectado = true;
                        }
                    }else{//La entidad 1 esta por debajo de la entidad 2
                        if(posicionY+alto<=e.get_posicion_y()){//Chequeo si la parte de arriba de entidad 1 esta chocando con la parte mas baja
                            detectado = true;
                        }
                    }
            }
        }
        return detectado;
    }
}
