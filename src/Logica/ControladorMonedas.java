package Logica;

public class ControladorMonedas {

    private int cantMonedas;
    private int sumarNVidas;

    public ControladorMonedas(){
        cantMonedas = 0;
        sumarNVidas = 0;
    }

    public void agregarMoneda(){
        if (cantMonedas >= 100) {
            cantMonedas = 0;
            sumarNVidas++;
        }
        else {
            cantMonedas++;
        }
    }

    protected int getMonedas(){
        return cantMonedas;
    }

    protected int agregarNVidas(){
        int toReturn = sumarNVidas;

        if (toReturn > 0){
            sumarNVidas = 0;
        }

        return toReturn;
    }

    protected int getNVidas(){
        return sumarNVidas;
    }

}
