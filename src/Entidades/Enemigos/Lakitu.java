package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Enemigos.EstadoLakitu.EstadoLakitu;
import Entidades.Enemigos.EstadoLakitu.LakituMoviendose;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import IA.IAatacar;
import Visitor.Visitor;

import java.util.List;
import java.util.SortedMap;

public class Lakitu extends Enemigo {
protected Jugador jugador;
protected EstadoLakitu estadoLakitu;
protected List<Spiny> arsenal;
    public Lakitu(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel, Jugador mario, List<Spiny> arsenal) {
        super(x, y, sprite, new IAatacar(mario), listaEnemigoNivel);
        velocidad = 4;
        jugador = mario;
        this.arsenal = arsenal;
        estadoLakitu=new LakituMoviendose(mario,this,this.getComportamientoIA());

    }

    public void actualizar() {
        this.actualizarEntidad();
        estadoLakitu.actualizarLakitu();
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
            if (mario.colisionAbajo(this)) {
                this.setAEliminar();
                toReturn = ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO;
            } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {

                    this.setAEliminar();
                    toReturn = (ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO);
                }
        return toReturn;
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int puntajeLakituDestruido = ConstantesPuntaje.PUNTAJE_LAKITU_DESTRUIDO;
        this.setAEliminar();
        proyectil.setDireccion(0);
        return puntajeLakituDestruido;
    }

    public void setEstadoLakitu(EstadoLakitu estadoLakitu) {
        this.estadoLakitu = estadoLakitu;
    }

    public boolean lanzar(){
        boolean pudeLanzar=true;
        if(arsenal.isEmpty()){
            this.setAEliminar();
            pudeLanzar=false;
        }else{
            Spiny spiny = arsenal.getFirst();
            spiny.getHitbox().setBounds(spiny.getPosicionEnX(),spiny.getPosicionEnY(),32,32);
            spiny.actualizar();
            arsenal.removeFirst();
        }
        return pudeLanzar;
    }

}
