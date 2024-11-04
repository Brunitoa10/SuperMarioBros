package Vista.Paneles;

import Constantes.CadenasValidacion;
import Constantes.ConstantesVista;
import Generador.GestorSonido.Sonido;
import Vista.Controladores.ControladorVistaJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPantallaNombreUsuario extends JPanel {

    protected static final long serialVersionUID = 1L;
    protected JTextField campoNombre;
    protected ControladorVistaJuego controladorVista;
    protected JButton botonConfirmarNombre;
    protected JLabel etiquetaNombre;
    protected Sonido sonidoBoton;

    public PanelPantallaNombreUsuario(ControladorVistaJuego controladorVista) {
        this.controladorVista = controladorVista;
        setLayout(null);
        setSize(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO);

        agregarCampoNombre();
        agregarBotonConfirmar();
    }

    protected void agregarCampoNombre() {
        campoNombre = new JTextField(20);
        campoNombre.setHorizontalAlignment(SwingConstants.CENTER);
        campoNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
        campoNombre.setBounds(295, 354, 200, 30);

        // Agregar un ActionListener para detectar la tecla Enter
        campoNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarNombre();
            }
        });

        add(campoNombre);
    }

    protected void agregarBotonConfirmar() {
        botonConfirmarNombre = new JButton("Confirmar");
        botonConfirmarNombre.setBounds(343, 425, 100, 30);
        decorarBotonConfirmar();
        add(botonConfirmarNombre);

        etiquetaNombre = new JLabel();
        etiquetaNombre.setIcon(new ImageIcon(PanelPantallaNombreUsuario.class.getResource("/Recursos/Imagenes/original/usuario.png")));
        etiquetaNombre.setBounds(0, 0, 800, 589);
        add(etiquetaNombre);

        registrarOyenteBotonConfirmar();
    }

    protected void decorarBotonConfirmar() {
        botonConfirmarNombre.setBackground(new Color(255, 255, 255));
    }

    protected void registrarOyenteBotonConfirmar() {
        botonConfirmarNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmarNombre();
            }
        });
    }

    protected void registrarOyenteMouseBoton(JButton boton) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sonidoBoton.reproducir();
            }
        });
    }

    private void confirmarNombre() {
        String nombreUsuario = campoNombre.getText().trim();

        if (!nombreUsuario.isEmpty() && nombreUsuario.matches(CadenasValidacion.VALIDACION_USUARIO)) {
            controladorVista.setNombreUsuario(nombreUsuario);
            controladorVista.mostrarPantallaModoJuego();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre válido. Solo se permiten letras y espacios.");
        }
    }
}
