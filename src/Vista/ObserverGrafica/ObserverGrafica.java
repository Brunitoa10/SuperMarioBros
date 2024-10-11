package Vista.ObserverGrafica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entidades.EntidadLogica;
import Vista.AdaptadorPosicionPixel;

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
        int x = AdaptadorPosicionPixel.transformar_x(entidad_observada.get_posicion_x());
        int y = AdaptadorPosicionPixel.transformar_y(entidad_observada.get_posicion_y());
        int ancho = this.getIcon().getIconWidth();
        int alto = this.getIcon().getIconHeight();
        setBounds(x, y, ancho, alto);

    }
}
