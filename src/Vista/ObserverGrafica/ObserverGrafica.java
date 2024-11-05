package Vista.ObserverGrafica;

import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entidades.EntidadLogica;
import Vista.AdaptadorPosicionPixel;

public abstract class ObserverGrafica extends JLabel implements Observer {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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

        setIcon(icono);
    }

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
