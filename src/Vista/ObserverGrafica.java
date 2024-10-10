package Vista;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entidades.EntidadLogica;

public class ObserverGrafica extends JLabel implements Observer {

    private EntidadLogica entidad_observada;

    protected ObserverGrafica(EntidadLogica entidad_observada) {
        super();
        this.entidad_observada = entidad_observada;
    }

    @Override
    public void actualizar_observer() {
        actualizar_imagen();
        actualizar_posicion_tamanio();
    }

    protected void actualizar_imagen() {
        String ruta_imagen = entidad_observada.get_sprite().get_ruta_imagen();
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(ruta_imagen));
        setIcon(icono);
    }

    protected void actualizar_posicion_tamanio() {

    }
}
