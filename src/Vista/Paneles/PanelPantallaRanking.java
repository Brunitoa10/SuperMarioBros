package Vista.Paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Logica.JugadorRanking;
import Logica.Ranking;
import Vista.Controladores.ControladorVista;

public class PanelPantallaRanking extends JPanel {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected final JTable tabla_jugadores;
	protected final Ranking ranking;
	protected final JButton btn_volver;

	protected JLabel lbl_columna_izquierda_1;
	protected JLabel lbl_columna_izquierda_2;
	protected JLabel lbl_columna_derecha_3;
	protected JLabel lbl_columna_izquierda_4;
	protected JLabel lbl_imagen_mario;
	protected JLabel lbl_imagen_hongo;
	protected JLabel lbl_columna_derecha_1;
	protected JLabel lbl_columna_derecha_2;
	protected JLabel lbl_columna_izquierda_3;
	protected JLabel lbl_columna_derecha_4;
	protected JLabel lbl_super;

	protected final ControladorVista controlador_vistas;

	public PanelPantallaRanking(ControladorVista controlador_vistas, Ranking rankingParametro) {
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(0, 0, 0));
		this.controlador_vistas = controlador_vistas;
		this.ranking = rankingParametro;
		setLayout(null);

		tabla_jugadores = new JTable();
		tabla_jugadores.setBackground(new Color(192, 192, 192));
		tabla_jugadores.setBounds(189, 198, 453, 215);
		add(tabla_jugadores);

		btn_volver = new JButton("Volver");
		btn_volver.setBackground(Color.LIGHT_GRAY);
		btn_volver.setBounds(317, 493, 173, 50);
		add(btn_volver);

		crear_lbl_columna_derecha();
		crear_lbl_columna_izquierda();
		crear_lbl_adicionales();

		llenarTabla();
		registrar_oyente_boton_volver();
	}

	private void crear_lbl_adicionales() {
		lbl_imagen_mario = new JLabel("");
		lbl_imagen_mario.setIcon(new ImageIcon(
				Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/mario.png"))));
		lbl_imagen_mario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imagen_mario.setBounds(267, 493, 27, 50);
		add(lbl_imagen_mario);

		lbl_imagen_hongo = new JLabel("");
		lbl_imagen_hongo.setIcon(new ImageIcon(Objects
				.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Sprites/Originales/mushroom.png"))));
		lbl_imagen_hongo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imagen_hongo.setBounds(502, 493, 27, 50);
		add(lbl_imagen_hongo);

		lbl_super = new JLabel("");
		lbl_super.setIcon(new ImageIcon(
				Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/super.png"))));
		lbl_super.setBounds(254, 11, 285, 115);
		add(lbl_super);
	}

	private void crear_lbl_columna_derecha() {
		lbl_columna_derecha_1 = new JLabel("");
		lbl_columna_derecha_1.setIcon(new ImageIcon(Objects
				.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_derecha_1.setBounds(27, 58, 107, 107);
		add(lbl_columna_derecha_1);

		lbl_columna_derecha_2 = new JLabel("");
		lbl_columna_derecha_2.setIcon(new ImageIcon(Objects
				.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_derecha_2.setBounds(27, 188, 107, 107);
		add(lbl_columna_derecha_2);

		lbl_columna_derecha_3 = new JLabel("");
		lbl_columna_derecha_3.setIcon(new ImageIcon(Objects
				.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_derecha_3.setBounds(27, 322, 107, 107);
		add(lbl_columna_derecha_3);

		lbl_columna_derecha_4 = new JLabel("");
		lbl_columna_derecha_4.setIcon(new ImageIcon(Objects
				.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_derecha_4.setBounds(27, 446, 107, 107);
		add(lbl_columna_derecha_4);
	}

	private void crear_lbl_columna_izquierda() {
		lbl_columna_izquierda_1 = new JLabel("");
		lbl_columna_izquierda_1.setIcon(new ImageIcon(Objects
				.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_izquierda_1.setBounds(675, 58, 107, 107);
		add(lbl_columna_izquierda_1);

		lbl_columna_izquierda_2 = new JLabel("");
		lbl_columna_izquierda_2.setIcon(new ImageIcon(Objects
				.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_izquierda_2.setBounds(675, 188, 107, 107);
		add(lbl_columna_izquierda_2);

		lbl_columna_izquierda_3 = new JLabel("");
		lbl_columna_izquierda_3.setIcon(new ImageIcon(Objects
				.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_izquierda_3.setBounds(675, 322, 107, 107);
		add(lbl_columna_izquierda_3);

		lbl_columna_izquierda_4 = new JLabel("");
		lbl_columna_izquierda_4.setIcon(new ImageIcon(Objects
				.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_izquierda_4.setBounds(675, 446, 107, 107);
		add(lbl_columna_izquierda_4);
	}

	protected void registrar_oyente_boton_volver() {
		btn_volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador_vistas.mostrar_pantalla_inicial();
			}
		});
	}

	public void llenarTabla() {
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {},
				new String[] { "Nombre", "Puntaje" });

		tabla_jugadores.setModel(tableModel);

		tabla_jugadores.setRowHeight(30);
		tabla_jugadores.setFont(new Font("Tahoma", Font.BOLD, 13));
		tabla_jugadores.setShowGrid(false);
		// tabla_jugadores.setSelectionBackground(new Color(255, 204, 51));
		tabla_jugadores.setShowGrid(false); // Ocultar la cuadrícula
		tabla_jugadores.setOpaque(false); // Hacer la tabla transparente
		tabla_jugadores.setFillsViewportHeight(true); // Asegurar que las celdas llenen el viewport

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tabla_jugadores.setDefaultRenderer(Object.class, centerRenderer);

		JTableHeader header = tabla_jugadores.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(102, 102, 102));
		header.setForeground(Color.WHITE);

		tabla_jugadores.setModel(tableModel);

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