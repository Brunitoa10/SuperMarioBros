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
        this.ancho= 32;
        this.alto = 32;



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

        System.out.println(posicionY);
        System.out.println(posicionY+alto);
        System.out.println("Mario");
        System.out.println(e.getPosicionEnX());
        System.out.println(e.getPosicionEnY()+e.getSprite().getAlto());
        if(posicionX<e.getPosicionEnX()){//Si la entidad receptora se encuentra a la izquierda de la del parametro
            System.out.println(posicionX+""+e.getPosicionEnX());
            System.out.println("EstoyIzquierda1");
            if(posicionX+ancho/2>=e.getPosicionEnX()-e.getSprite().getAncho()/2){//chequeo si llegan a chocarse la parte derecha de la receptora con la parte izquierda con la de del parametro
                System.out.println("EstoyIzquierda");
                System.out.println(detectado = posicionY >= e.getPosicionEnY());

                detectado = posicionY >= e.getPosicionEnY();
                }
            }else{//Si la entidad receptora se encuentra a la derecha de la que entra por parametro
                System.out.println("EstoyDerecha");
                System.out.println(posicionX+""+e.getPosicionEnX());
                    if(posicionX-ancho/2<=e.getPosicionEnX()+e.getSprite().getAncho()/2){//chequeo si llegan a chocarse la parte izquierda de la receptora con la parte derecha con la de del parametro
                        System.out.println(posicionX-ancho/2);
                        System.out.println(e.getPosicionEnX()+e.getSprite().getAncho()/2);
                            System.out.println(detectado = posicionY >= e.getPosicionEnY());
                            detectado = posicionY >= e.getPosicionEnY();
                    }

            }
    if(detectado){
        if(e.getPosicionEnX()>=posicionX-ancho/2 && e.getPosicionEnX()<=posicionX+ancho/2){
            if(e.getPosicionEnY()>posicionY+alto){
                System.out.println("Estoy arriba, cambiar Piso");
            }

        }
    }

        return detectado;
    }
}
