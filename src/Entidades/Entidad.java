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
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public int getPosicionEnX() {
        return posicionX;
    }

    public void setPosicionEnX(int x) {
        posicionX = x;
    }

    @Override
    public int getPosicionEnY() {
        return posicionY;
    }

    public void setPosicionEnY(int y) {
        posicionY = y;
    }

    public void registrar_observer(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void actualizarEntidad() {
        observer.actualizarObserver();
    }

    public boolean detectColission(Entidad e){
        boolean detectado = false;
        if(posicionX<e.getPosicionEnX()){//Si la entidad receptora se encuentra a la izquierda de la del parametro
            if(posicionX+ancho/2>=e.getPosicionEnX()-e.getSprite().getAncho()/2){//chequeo si llegan a chocarse la parte derecha de la receptora con la parte izquierda con la de del parametro
                if(posicionY>=e.getPosicionEnX()){//Si la entidad 1 esta arriba de la entidad 2 chequeo que se toquen con la posicion Y sin contemplar la altura y Entidad 2 contemplandola
                    if(posicionY<=e.getPosicionEnY()+e.getSprite().getAlto()){//Chequeo si la parte de abajo de entidad 1 esta chocando con la parte mas alta de entidad 2
                        detectado = true;
                    }
                }else{//La entidad 1 esta por debajo de la entidad 2
                    if(posicionY+alto<=e.getPosicionEnY()){//Chequeo si la parte de arriba de entidad 1 esta chocando con la parte mas baja
                        detectado = true;
                    }
                }
            }else{//Si la entidad receptora se encuentra a la derecha de la que entra por parametro
                    if(posicionX-ancho/2<=e.getPosicionEnX()+e.getSprite().getAncho()/2){//chequeo si llegan a chocarse la parte izquierda de la receptora con la parte derecha con la de del parametro
                        if(posicionY<=e.getPosicionEnY()+e.getSprite().getAlto()){//Chequeo si la parte de abajo de entidad 1 esta chocando con la parte mas alta de entidad 2
                            detectado = true;
                        }
                    }else{//La entidad 1 esta por debajo de la entidad 2
                        if(posicionY+alto<=e.getPosicionEnY()){//Chequeo si la parte de arriba de entidad 1 esta chocando con la parte mas baja
                            detectado = true;
                        }
                    }
            }
        }
        return detectado;
    }
}
