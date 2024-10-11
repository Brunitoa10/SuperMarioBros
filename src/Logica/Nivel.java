package Logica;

import Entidades.Entidad;
import Fabricas.FabricaEntidad;

public class Nivel {

    protected Entidad[] entidades;
    protected int vida;
    protected int puntajeTotal;
    protected FabricaEntidad fabricaEntidades;


    public Nivel() {

        entidades = new Entidad[vida]; // a esto le falta
        int vida = 3;
        int puntajeTotal = 0;
        // fabricaEntidades = new FabricaEntidad() hay q hacer esto tmb


    }

    public void sumarVida(int cantVidas){
        vida += cantVidas;
    }

    public void perdioVida(){
        vida--;
    }

    public boolean sinVidas(){
        return vida > 0;
    }

    public Nivel generarNivel(int nivel){
        Nivel toReturn = new Nivel();

        //para hacer

        return toReturn;
    }

    public void actualizarObserver(){

        //para hacer

    }




}
