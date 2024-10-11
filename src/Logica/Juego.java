package Logica;

import Fabricas.FabricaEntidad;
import Vista.Controladores.ControladorVistaJuego;

public class Juego {

    protected ControladorVistaJuego controlador_vistas;
    // protected GeneradorNivel generadorNivel;
    // protected FabricaSprites fabricaSprites;
    protected FabricaEntidad fabricaEntidad;
    protected Nivel nivelActual;

    // Comunicacion con parte grafica
    public void set_controlador_vistas(ControladorVistaJuego controlador_vistas) {
        this.controlador_vistas = controlador_vistas;
    }

    public void iniciar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iniciar'");
    }

}
