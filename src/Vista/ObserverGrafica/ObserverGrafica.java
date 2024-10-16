package Vista.ObserverGrafica;

import Entidades.EntidadLogica;
import Vista.AdaptadorPosicionPixel;
import java.awt.MediaTracker;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ObserverGrafica extends JLabel implements Observer {

    private EntidadLogica entidad_observada;

    protected ObserverGrafica(EntidadLogica entidad_observada) {
        super();
        this.entidad_observada = entidad_observada;
    }

    @Override
    public void actualizarObserver() {
        actualizar_imagen();
        actualizar_posicion_tamanio();
    }


    protected void actualizar_imagen() {

        String rutaImagen = entidad_observada.getSprite().getRutaImagen();
        ImageIcon icono = new ImageIcon(rutaImagen);

        if (icono.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.err.println("Error al cargar la imagen: " + rutaImagen);
        } else {
            setIcon(icono);
        }
    }

    /*
     * protected void actualizar_posicion_tamanio() {
     * int x =
     * AdaptadorPosicionPixel.transformar_x(entidad_observada.get_posicion_x());
     * int y =
     * AdaptadorPosicionPixel.transformar_y(entidad_observada.get_posicion_y());
     * int ancho = this.getIcon().getIconWidth();
     * int alto = this.getIcon().getIconHeight();
     * setBounds(x, y, ancho, alto);
     * }
     */
    protected void actualizar_posicion_tamanio() {
        int x = AdaptadorPosicionPixel.transformar_x(entidad_observada.getPosicionEnX());
        int y = AdaptadorPosicionPixel.transformar_y(entidad_observada.getPosicionEnY());

        if (getIcon() != null) {
            int ancho = getIcon().getIconWidth();
            int alto = getIcon().getIconHeight();
            setBounds(x, y, ancho, alto);
        }
    }
}
