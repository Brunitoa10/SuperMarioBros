package Entidades;

import Constantes.ConstantesBloques;
import Entidades.EntidadInmovil.EntidadInmovil;
import Fabricas.Sprite;
import Logica.Temporizador;
import Visitor.Visitor;

import java.util.List;

public class Vacio extends EntidadInmovil {

    protected Temporizador temporizador;
    protected boolean animacionActiva;
    protected List<Vacio> listaVacioNivel;
    protected int cantidadSegundos = 0;

    public Vacio(int x, int y, Sprite sprite, List<Vacio> listaVacioNivel) {
        super(x, y, sprite);
        temporizador = new Temporizador();
        animacionActiva = false;
        this.listaVacioNivel = listaVacioNivel;
    }

    @Override
    public boolean detectarColision(Entidad c) {
        return false;
    }

    @Override
    public int accept(Visitor v) {

        return 0;
    }

    public void actualizarAnimacion() {
        super.actualizarEntidad();
        if (temporizador.hanPasadoNSegundos(cantidadSegundos)) {
            this.getSprite().setRutaImagen(ConstantesBloques.SPRITE_VACIA);
        }

    }

    public void setAnimacionFinal(int segundos) {
        temporizador.iniciar();
        cantidadSegundos = segundos;
        animacionActiva = true;
    }

    public void actualizar() {
        if (animacionActiva) {
            actualizarAnimacion();

        }
    }

    @Override
    public void eliminarEntidad() {
        listaVacioNivel.remove(this);
    }
}
