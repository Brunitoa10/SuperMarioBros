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

    public boolean detectColission(EntidadMovil e){
        boolean detectado = false;


        if(posicionX<e.getPosicionEnX()){//Si la entidad receptora se encuentra a la izquierda de la del parametro


            if(posicionX+ancho/2>=e.getPosicionEnX()-e.getSprite().getAncho()/2){//chequeo si llegan a chocarse la parte derecha de la receptora con la parte izquierda con la de del parametro
                System.out.println("EstoyIzquierda");
                System.out.println(detectado = posicionY >= e.getPosicionEnY());

                detectado = posicionY >= e.getPosicionEnY();
                }
            }else{//Si la entidad receptora se encuentra a la derecha de la que entra por parametro
                System.out.println("EstoyDerecha");
                    if(posicionX-ancho/2<=e.getPosicionEnX()+e.getSprite().getAncho()/2){//chequeo si llegan a chocarse la parte izquierda de la receptora con la parte derecha con la de del parametro
                            detectado = posicionY >= e.getPosicionEnY();
                    }

            }

        int ExtremoIzquierdo=this.getPosicionEnX()-this.getSprite().getAncho()/2;
        int ExtremoDerecho=this.getPosicionEnX()+this.getSprite().getAncho()/2;
        if(e.getPosicionEnX()>ExtremoIzquierdo && e.getPosicionEnX()<ExtremoDerecho){
            System.out.println("EstoyEnElMedio");
            System.out.println(this.getPosicionEnY()+this.getSprite().getAlto());
            System.out.println(e.getPosicionEnY());
            if(this.getPosicionEnY()-this.getSprite().getAlto()-5>e.getPosicionEnY()){
                e.setPiso(this.getPosicionEnY()-this.getSprite().getAlto()-5);
                detectado=true;
            }
    }
        return detectado;
    }
}
