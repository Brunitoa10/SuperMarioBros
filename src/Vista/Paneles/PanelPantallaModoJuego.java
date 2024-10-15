package Vista.Paneles;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;

public class PanelPantallaModoJuego extends JPanel {

    protected static final long serialVersionUID = 1L;
    protected JButton btn_original;
    protected JButton btn_alternativo;
    protected ControladorVista controlador_vistas;

    public PanelPantallaModoJuego(ControladorVista controlador_vistas) {
        this.controlador_vistas = controlador_vistas;
        setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));
        setLayout(null);

        crear_botones();
        registrar_oyente_botones();
    }

    private void crear_botones() {
        btn_original = new JButton("New button");
        btn_original
                .setIcon(new ImageIcon(PanelPantallaModoJuego.class.getResource("/Recursos/Imagenes/original.png")));
        btn_original.setBounds(0, 0, 393, 600);
        add(btn_original);

        btn_alternativo = new JButton("New button");
        btn_alternativo
                .setIcon(new ImageIcon(PanelPantallaModoJuego.class.getResource("/Recursos/Imagenes/alternativo.png")));
        btn_alternativo.setBounds(394, 0, 406, 600);
        add(btn_alternativo);
    }

    private void registrar_oyente_botones() {
        registrar_oyente_boton_original();
        registrar_oyente_boton_alternativo();
    }

    // Método para manejar la selección del modo de juego
    private void seleccionarModo(String modo) {
        // Aquí podrías agregar la lógica para cambiar a otro panel o iniciar el modo de
        // juego
        System.out.println("Modo seleccionado: " + modo);
        // Ejemplo: Cambiar panel en la GUI principal
        controlador_vistas.cambiar_modo_juego(modo);
    }

    private void registrar_oyente_boton_original() {
        btn_original.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarModo("original");
            }
        });
    }

    private void registrar_oyente_boton_alternativo() {
        btn_alternativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarModo("alternativo");
            }
        });
    }
}
