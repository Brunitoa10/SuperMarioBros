package Vista.Paneles;

import Logica.JugadorRanking;
import Logica.Ranking;
import Vista.Controladores.ControladorVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class PanelPantallaRanking extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTable tablaJugadores = new JTable();
	private final Ranking ranking;
	private final JButton btn_volver;
	private final ControladorVista controlador_vistas;

	public PanelPantallaRanking(ControladorVista controlador_vistas, Ranking rankingParametro){
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(0, 0, 0));
		this.controlador_vistas = controlador_vistas;
		this.ranking = rankingParametro;
		setLayout(null);
		tablaJugadores.setBackground(new Color(192, 192, 192));
		tablaJugadores.setBounds(181, 119, 453, 346);
		add(tablaJugadores);

		JLabel lbl_columna_derecha_1 = new JLabel("");
		lbl_columna_derecha_1.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_derecha_1.setBounds(27, 58, 107, 107);
		add(lbl_columna_derecha_1);

		JLabel lbl_columna_derecha_3 = new JLabel("");
		lbl_columna_derecha_3.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_derecha_3.setBounds(27, 322, 107, 107);
		add(lbl_columna_derecha_3);

		btn_volver = new JButton("Volver");
		btn_volver.setBackground(Color.LIGHT_GRAY);
		btn_volver.setBounds(317, 493, 173, 50);
		add(btn_volver);

		JLabel lbl_imagen_mario = new JLabel("");
		lbl_imagen_mario.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/mario.png"))));
		lbl_imagen_mario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imagen_mario.setBounds(267, 493, 27, 50);
		add(lbl_imagen_mario);

		JLabel lbl_imagen_hongo = new JLabel("");
		lbl_imagen_hongo.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Sprites/Originales/mushroom.png"))));
		lbl_imagen_hongo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imagen_hongo.setBounds(502, 493, 27, 50);
		add(lbl_imagen_hongo);

		JLabel lbl_columna_derecha_2 = new JLabel("");
		lbl_columna_derecha_2.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_derecha_2.setBounds(27, 188, 107, 107);
		add(lbl_columna_derecha_2);

		JLabel lbl_columna_izquierda_1 = new JLabel("");
		lbl_columna_izquierda_1.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_izquierda_1.setBounds(675, 58, 107, 107);
		add(lbl_columna_izquierda_1);

		JLabel lbl_columna_izquierda_2 = new JLabel("");
		lbl_columna_izquierda_2.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_izquierda_2.setBounds(675, 188, 107, 107);
		add(lbl_columna_izquierda_2);

		JLabel lbl_columna_izquierda_3 = new JLabel("");
		lbl_columna_izquierda_3.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_izquierda_3.setBounds(675, 322, 107, 107);
		add(lbl_columna_izquierda_3);

		JLabel lbl_super = new JLabel("");
		lbl_super.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/super.png"))));
		lbl_super.setBounds(254, 11, 285, 115);
		add(lbl_super);

		JLabel lbl_columna_derecha_4 = new JLabel("");
		lbl_columna_derecha_4.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_derecha_4.setBounds(27, 446, 107, 107);
		add(lbl_columna_derecha_4);

		JLabel lbl_columna_izquierda_4 = new JLabel("");
		lbl_columna_izquierda_4.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png"))));
		lbl_columna_izquierda_4.setBounds(675, 446, 107, 107);
		add(lbl_columna_izquierda_4);

		llenarTabla();
		registrar_oyente_boton_volver();
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
				new Object[][]{},
				new String[]{"Nombre", "Puntaje"}
		);

		tablaJugadores.setModel(tableModel);


		tablaJugadores.setRowHeight(30);
		tablaJugadores.setFont(new Font("Arial", Font.PLAIN, 18));
		tablaJugadores.setShowGrid(false);
		tablaJugadores.setSelectionBackground(new Color(255, 204, 51));


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