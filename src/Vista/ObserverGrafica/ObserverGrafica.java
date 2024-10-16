package Vista.ObserverGrafica;

import Entidades.EntidadLogica;
import Vista.AdaptadorPosicionPixel;
import java.awt.MediaTracker;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ObserverGrafica extends JLabel implements Observer {

    private EntidadLogica entidadObservada;

    protected ObserverGrafica(EntidadLogica entidadObservada) {
        super();
        this.entidadObservada = entidadObservada;
    }

    @Override
    public void actualizarObserver() {
        actualizarImagen();
        actualizarPosicionTamanio();
    }


    protected void actualizarImagen() {

        String rutaImagen = entidadObservada.getSprite().getRutaImagen();
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
    protected void actualizarPosicionTamanio() {
        int x = AdaptadorPosicionPixel.transformarX(entidadObservada.getPosicionEnX());
        int y = AdaptadorPosicionPixel.transformarY(entidadObservada.getPosicionEnY());

        if (getIcon() != null) {
            int ancho = getIcon().getIconWidth();
            int alto = getIcon().getIconHeight();
            setBounds(x, y, ancho, alto);
        }
    }
}
