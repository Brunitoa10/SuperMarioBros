package Generador;

import Fabricas.*;
import Logica.Nivel;

public class GeneradorNivel {
    protected CreadorEntidad fabricaEntidades;

    public GeneradorNivel(CreadorEntidad fabricaEntidades) {
        this.fabricaEntidades = fabricaEntidades;
    }

    public void cambiarFabricaEntidades(CreadorEntidad fabricaEntidades) {
        this.fabricaEntidades = fabricaEntidades;
    }

    public Nivel generarNivel(String ruta_archivo) {
        Nivel nivel = new Nivel(ruta_archivo);
        return nivel;
    }
}