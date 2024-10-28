package Vista.Paneles;

import Constantes.ConstantesVista;
import Vista.Controladores.ControladorVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPantallaModoJuego extends JPanel {

    protected static final long serialVersionUID = 1L;
    protected JButton btnOriginal;
    protected JButton btnAlternativo;
    protected ControladorVista controladorVistas;


    public PanelPantallaModoJuego(ControladorVista controladorVistas) {
        this.controladorVistas = controladorVistas;
        setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));
        setLayout(null);

        crearBotones();
        registrarOyenteBotones();
    }

    private void crearBotones() {
        btnOriginal = new JButton("btn_original");
        btnOriginal.setIcon(new ImageIcon(PanelPantallaModoJuego.class.getResource("/Recursos/Imagenes/original/original.png")));
        btnOriginal.setBounds(0, 0, 393, 600);
        add(btnOriginal);

        btnAlternativo = new JButton("btn_alternatvo");
        btnAlternativo.setIcon(new ImageIcon(PanelPantallaModoJuego.class.getResource("/Recursos/Imagenes/alternativo/alternativo.png")));
        btnAlternativo.setBounds(394, 0, 406, 600);
        add(btnAlternativo);
    }

    private void registrarOyenteBotones() {
        registrarOyenteBotonOriginal();
        registrarOyenteBotonAlternativo();
    }

    private void seleccionarModo(String modo) {
        controladorVistas.cambiarModoJuego(modo);
    }

    private void registrarOyenteBotonOriginal() {
        btnOriginal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarModo("original");
            }
        });
    }

    private void registrarOyenteBotonAlternativo() {
        btnAlternativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarModo("alternativo");
            }
        });
    }
}
