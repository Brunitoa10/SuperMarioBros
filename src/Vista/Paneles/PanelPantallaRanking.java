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

import GestorArchivos.JugadorRanking;
import GestorArchivos.Ranking;
import Vista.Controladores.ControladorVista;

public class PanelPantallaRanking extends JPanel {

	protected static final long serialVersionUID = 1L;
	protected JTable tablaJugadores;
	protected Ranking ranking;
	protected JButton btnVolver;

	protected JLabel lblColumnaIzquierda1;
	protected JLabel lblColumnaIzquierda2;
	protected JLabel lblColumnaDerecha3;
	protected JLabel lblColumnaIzquierda4;
	protected JLabel lblImagenMario;
	protected JLabel lblImagenHongo;
	protected JLabel lblColumnaDerecha1;
	protected JLabel lblColumnaDerecha2;
	protected JLabel lblColumnaIzquierda3;
	protected JLabel lblColumnaDerecha4;
	protected JLabel lblSuper;

	protected ControladorVista controladorVistas;
	protected String modoJuego;

	public PanelPantallaRanking(ControladorVista controladorVistas, Ranking rankingParametro) {
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(0, 0, 0));
		this.controladorVistas = controladorVistas;
		this.ranking = rankingParametro;
		setLayout(null);

		modoJuego = controladorVistas.obtenerModoJuego();

		tablaJugadores = new JTable();
		tablaJugadores.setBackground(new Color(192, 192, 192));
		tablaJugadores.setBounds(189, 198, 453, 215);
		add(tablaJugadores);

		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.LIGHT_GRAY);
		btnVolver.setBounds(317, 493, 173, 50);
		add(btnVolver);

		crearLblColumnaDerecha();
		crearLblColumnaIzquierda();
		crearLblAdicionales();

		llenarTabla();
		registrarOyenteBotonVolver();
	}

	private void registrarOyenteBotonVolver() {
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorVistas.volverAlPanelAnterior();
			}
		});
	}

	public void llenarTabla() {
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Puntaje" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tableModel.setRowCount(0);

		Iterable<JugadorRanking> listaJugadores = ranking.mostrarRanking();
		for (JugadorRanking jugador : listaJugadores) {
			Object[] fila = new Object[2];
			fila[0] = jugador.getNombre();
			fila[1] = jugador.getPuntaje();
			tableModel.addRow(fila);
		}

		// Configurar la tabla
		tablaJugadores.setModel(tableModel);
		tablaJugadores.setRowHeight(30);
		tablaJugadores.setFont(new Font("Tahoma", Font.BOLD, 13));
		tablaJugadores.setShowGrid(false);
		tablaJugadores.setOpaque(false);
		tablaJugadores.setFillsViewportHeight(true);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaJugadores.setDefaultRenderer(Object.class, centerRenderer);

		JTableHeader header = tablaJugadores.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(102, 102, 102));
		header.setForeground(Color.WHITE);
	}
	
	private void crearLblAdicionales() {
		lblImagenMario = new JLabel("");
		lblImagenMario.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/mario.png"))));
		lblImagenMario.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenMario.setBounds(267, 493, 27, 50);
		add(lblImagenMario);

		lblImagenHongo = new JLabel("");
		lblImagenHongo.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Sprites/" + modoJuego + "/mushroom.png"))));
		lblImagenHongo.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenHongo.setBounds(502, 493, 27, 50);
		add(lblImagenHongo);

		lblSuper = new JLabel("");
		lblSuper.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/super.png"))));
		lblSuper.setBounds(254, 11, 285, 115);
		add(lblSuper);
	}

	private void crearLblColumnaDerecha() {
		lblColumnaDerecha1 = new JLabel("");
		lblColumnaDerecha1.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/bloque_pregunta.png"))));
		lblColumnaDerecha1.setBounds(27, 58, 107, 107);
		add(lblColumnaDerecha1);

		lblColumnaDerecha2 = new JLabel("");
		lblColumnaDerecha2.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/bloque_pregunta.png"))));
		lblColumnaDerecha2.setBounds(27, 188, 107, 107);
		add(lblColumnaDerecha2);

		lblColumnaDerecha3 = new JLabel("");
		lblColumnaDerecha3.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/bloque_pregunta.png"))));
		lblColumnaDerecha3.setBounds(27, 322, 107, 107);
		add(lblColumnaDerecha3);

		lblColumnaDerecha4 = new JLabel("");
		lblColumnaDerecha4.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/bloque_pregunta.png"))));
		lblColumnaDerecha4.setBounds(27, 446, 107, 107);
		add(lblColumnaDerecha4);
	}

	private void crearLblColumnaIzquierda() {
		lblColumnaIzquierda1 = new JLabel("");
		lblColumnaIzquierda1.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/bloque_pregunta.png"))));
		lblColumnaIzquierda1.setBounds(675, 58, 107, 107);
		add(lblColumnaIzquierda1);

		lblColumnaIzquierda2 = new JLabel("");
		lblColumnaIzquierda2.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/bloque_pregunta.png"))));
		lblColumnaIzquierda2.setBounds(675, 188, 107, 107);
		add(lblColumnaIzquierda2);

		lblColumnaIzquierda3 = new JLabel("");
		lblColumnaIzquierda3.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/bloque_pregunta.png"))));
		lblColumnaIzquierda3.setBounds(675, 322, 107, 107);
		add(lblColumnaIzquierda3);

		lblColumnaIzquierda4 = new JLabel("");
		lblColumnaIzquierda4.setIcon(new ImageIcon(Objects.requireNonNull(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/" + modoJuego + "/bloque_pregunta.png"))));
		lblColumnaIzquierda4.setBounds(675, 446, 107, 107);
		add(lblColumnaIzquierda4);
	}
}
