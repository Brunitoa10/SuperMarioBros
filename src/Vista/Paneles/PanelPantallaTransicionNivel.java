package Vista.Paneles;

import java.awt.*;


import javax.swing.*;

import Vista.GUI;

public class PanelPantallaTransicionNivel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JLabel gifLabel;
	protected Timer temporizador;
	protected GUI controlador;
	protected String gifPath;
	protected ImageIcon gifIcon;

    public PanelPantallaTransicionNivel(GUI controlador, int nivel) {
        this.controlador = controlador;
        setLayout(new BorderLayout());
        
        gifPath = "/Recursos/GifsPaneles/" + nivel + ".gif";
        gifIcon = new ImageIcon(getClass().getResource(gifPath));
        gifLabel = new JLabel(gifIcon);
        add(gifLabel, BorderLayout.CENTER);

        temporizador = new Timer(4000, e -> mostrarPantallaNivel());
        temporizador.setRepeats(false);
    }

    public void iniciarTransicion() {
        temporizador.start();
    }

    private void mostrarPantallaNivel() {
        temporizador.stop();
        controlador.mostrarPantallaNivel();
    }
}
