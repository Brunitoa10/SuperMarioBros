package Vista.Paneles;

import Logica.JugadorRanking;
import Logica.Ranking;
import Vista.Controladores.ControladorVista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class PanelPantallaRanking extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTable tablaJugadores;
	private final Ranking ranking;
	private final JButton btnVolver;
	
	private JLabel lblColumnaIzquierda1;
	private JLabel lblColumnaIzquierda2; 
	private JLabel lblColumnaDerecha3;
	private JLabel lblColumnaIzquierda4;
	private JLabel lblImagenMario;
	private JLabel lblImagenHongo;
	private JLabel lblColumnaDerecha1;
	private JLabel lblColumnaDerecha2; 
	private JLabel lblColumnaIzquierda3;
	private JLabel lblColumnaDerecha4;
	private JLabel lblSuper;
	
	private final ControladorVista controlador_vistas;

	public PanelPantallaRanking(ControladorVista controlador_vistas, Ranking rankingParametro){
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(0, 0, 0));
		this.controlador_vistas = controlador_vistas;
		this.ranking = rankingParametro;
		setLayout(null);
		
		tablaJugadores = new JTable();
		tablaJugadores.setBackground(new Color(192, 192, 192));
		tablaJugadores.setBounds(189, 198, 453, 215);
		add(tablaJugadores);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.LIGHT_GRAY);
		btnVolver.setBounds(317, 493, 173, 50);
		add(btnVolver);
		
		crear_lbl_columna_derecha();
		crear_lbl_columna_izquierda();
		crear_lbl_adicionales();
		
		llenarTabla();
		registrar_oyente_boton_volver();
	}

	private void crear_lbl_adicionales() {
		lblImagenMario = new JLabel("");
		lblImagenMario.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/mario.png"))));
		lblImagenMario.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenMario.setBounds(267, 493, 27, 50);
		add(lblImagenMario);

		lblImagenHongo = new JLabel("");
		lblImagenHongo.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Sprites/Originales/mushroom.png"))));
		lblImagenHongo.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenHongo.setBounds(502, 493, 27, 50);
		add(lblImagenHongo);


		lblSuper = new JLabel("");
		lblSuper.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/super.png"))));
		lblSuper.setBounds(254, 11, 285, 115);
		add(lblSuper);
	}

	private void crear_lbl_columna_derecha() {
		lblColumnaDerecha1 = new JLabel("");
		lblColumnaDerecha1.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lblColumnaDerecha1.setBounds(27, 58, 107, 107);
		add(lblColumnaDerecha1);
		
		lblColumnaDerecha2 = new JLabel("");
		lblColumnaDerecha2.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lblColumnaDerecha2.setBounds(27, 188, 107, 107);
		add(lblColumnaDerecha2);
		
		lblColumnaDerecha3 = new JLabel("");
		lblColumnaDerecha3.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lblColumnaDerecha3.setBounds(27, 322, 107, 107);
		add(lblColumnaDerecha3);
		
		lblColumnaDerecha4 = new JLabel("");
		lblColumnaDerecha4.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lblColumnaDerecha4.setBounds(27, 446, 107, 107);
		add(lblColumnaDerecha4);
	}
	
	private void crear_lbl_columna_izquierda() {
		lblColumnaIzquierda1 = new JLabel("");
		lblColumnaIzquierda1.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lblColumnaIzquierda1.setBounds(675, 58, 107, 107);
		add(lblColumnaIzquierda1);

		lblColumnaIzquierda2 = new JLabel("");
		lblColumnaIzquierda2.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lblColumnaIzquierda2.setBounds(675, 188, 107, 107);
		add(lblColumnaIzquierda2);

		lblColumnaIzquierda3 = new JLabel("");
		lblColumnaIzquierda3.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lblColumnaIzquierda3.setBounds(675, 322, 107, 107);
		add(lblColumnaIzquierda3);
		
		lblColumnaIzquierda4 = new JLabel("");
		lblColumnaIzquierda4.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lblColumnaIzquierda4.setBounds(675, 446, 107, 107);
		add(lblColumnaIzquierda4);
	}


	protected void registrar_oyente_boton_volver() {
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador_vistas.mostrarPantallaInicial();
			}
		});
	}

	public void llenarTabla() {
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][]{},
				new String[]{"Nombre", "Puntaje"}
		);

		tablaJugadores.setModel(tableModel);


		tablaJugadores.setRowHeight(30);
		tablaJugadores.setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaJugadores.setShowGrid(false);
		//tabla_jugadores.setSelectionBackground(new Color(255, 204, 51));
		tablaJugadores.setShowGrid(false); // Ocultar la cuadrícula
		tablaJugadores.setOpaque(false); // Hacer la tabla transparente
		tablaJugadores.setFillsViewportHeight(true); // Asegurar que las celdas llenen el viewport

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaJugadores.setDefaultRenderer(Object.class, centerRenderer);


		JTableHeader header = tablaJugadores.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(102, 102, 102));
		header.setForeground(Color.WHITE);

		tablaJugadores.setModel(tableModel);

		Iterable<JugadorRanking> listaJugadores;
		listaJugadores = ranking.mostrarRanking();
		for (JugadorRanking jugador : listaJugadores) {
			Object[] fila = new Object[2];
			fila[0] = jugador.getNombre();
			fila[1] = jugador.getPuntaje();

			tableModel.addRow(fila);
		}
	}
}