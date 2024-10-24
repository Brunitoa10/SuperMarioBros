package Vista.Paneles;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

import Generador.GestorSonido.Sonido;
import GestorArchivos.CadenasValidacion;

public class PanelPantallaNombreUsuario extends JPanel {

	protected static final long serialVersionUID = 1L;
	protected JTextField campoNombre;
	protected ControladorVista controladorVista;
	protected JButton botonConfirmarNombre;
	protected JLabel etiquetaNombre;
	protected Sonido sonidoBoton;

	public PanelPantallaNombreUsuario(ControladorVista controladorVista) {
		this.controladorVista = controladorVista;
		setLayout(null);
		setSize(ConstantesVista.PANEL_ANCHO,ConstantesVista.PANEL_ALTO);

		agregarCampoNombre();
		agregarBotonConfirmar();
	}


	protected void agregarCampoNombre() {
		campoNombre = new JTextField(20);
		campoNombre.setHorizontalAlignment(SwingConstants.CENTER);
		campoNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoNombre.setBounds(295, 354, 200, 30);
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
	    
	    // Verificar que el nombre solo contenga letras y espacios
	    if (!nombreUsuario.isEmpty() && nombreUsuario.matches(CadenasValidacion.VALIDACION_USUARIO)) {
	        controladorVista.setNombreUsuario(nombreUsuario);
	        controladorVista.mostrarPantallaModoJuego();
	    } else {
	        JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre válido. Solo se permiten letras y espacios.");
	    }
	}
}
