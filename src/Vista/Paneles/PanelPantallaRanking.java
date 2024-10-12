package Vista.Paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;

public class PanelPantallaRanking extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lbl_puntaje_primero;
	private JLabel lbl_puntaje_segundo;
	private JLabel lbl_puntaje_tercero;
	private JLabel lbl_puntaje_cuarto;
	private JLabel lbl_puntaje_quinto;

	private JLabel lbl_columna_izquierda;
	private JLabel lbl_columna_izquierda_1;
	private JLabel lbl_columna_izquierda_2;
	private JLabel lbl_columna_izquierda_3;

	private JLabel lbl_columna_derecha_4;
	private JLabel lbl_columna_derecha_5;
	private JLabel lbl_columna_derecha_6;
	private JLabel lbl_columna_derecha_7;
	private JLabel lbl_super;

	private JLabel lbl_imagen_mario;
	private JLabel lbl_imagen_hongo;

	private JButton btn_volver;

	private ControladorVista controlador_vistas;

	public PanelPantallaRanking(ControladorVista controlador_vistas) {
		this.controlador_vistas = controlador_vistas;
		setBackground(new Color(0, 0, 0));
		setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));
		setLayout(null);

		cargar_pantalla();
		registrar_oyente_boton_volver();
	}

	private void cargar_pantalla() {

		lbl_puntaje_primero = new JLabel("0000");
		lbl_puntaje_primero.setForeground(new Color(255, 255, 255));
		lbl_puntaje_primero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_primero.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_primero.setBackground(new Color(192, 192, 192));
		lbl_puntaje_primero.setBounds(236, 150, 285, 34);
		add(lbl_puntaje_primero);

		lbl_puntaje_segundo = new JLabel("0000");
		lbl_puntaje_segundo.setForeground(new Color(255, 255, 255));
		lbl_puntaje_segundo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_segundo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_segundo.setBackground(Color.LIGHT_GRAY);
		lbl_puntaje_segundo.setBounds(236, 195, 285, 34);
		add(lbl_puntaje_segundo);

		lbl_puntaje_tercero = new JLabel("0000");
		lbl_puntaje_tercero.setForeground(new Color(255, 255, 255));
		lbl_puntaje_tercero.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_tercero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_tercero.setBackground(Color.LIGHT_GRAY);
		lbl_puntaje_tercero.setBounds(236, 240, 285, 34);
		add(lbl_puntaje_tercero);

		lbl_puntaje_cuarto = new JLabel("0000");
		lbl_puntaje_cuarto.setForeground(new Color(255, 255, 255));
		lbl_puntaje_cuarto.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_cuarto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_cuarto.setBackground(Color.LIGHT_GRAY);
		lbl_puntaje_cuarto.setBounds(236, 285, 285, 34);
		add(lbl_puntaje_cuarto);

		lbl_puntaje_quinto = new JLabel("0000");
		lbl_puntaje_quinto.setForeground(new Color(255, 255, 255));
		lbl_puntaje_quinto.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_quinto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_quinto.setBackground(Color.LIGHT_GRAY);
		lbl_puntaje_quinto.setBounds(236, 330, 285, 34);
		add(lbl_puntaje_quinto);

		lbl_columna_izquierda = new JLabel("");
		lbl_columna_izquierda.setIcon(
				new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png")));
		lbl_columna_izquierda.setBounds(10, 55, 107, 107);
		add(lbl_columna_izquierda);

		lbl_columna_izquierda_1 = new JLabel("");
		lbl_columna_izquierda_1.setIcon(
				new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png")));
		lbl_columna_izquierda_1.setBounds(10, 178, 107, 107);
		add(lbl_columna_izquierda_1);

		lbl_columna_izquierda_2 = new JLabel("");
		lbl_columna_izquierda_2.setIcon(
				new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png")));
		lbl_columna_izquierda_2.setBounds(10, 302, 107, 107);
		add(lbl_columna_izquierda_2);

		lbl_columna_izquierda_3 = new JLabel("");
		lbl_columna_izquierda_3.setIcon(
				new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png")));
		lbl_columna_izquierda_3.setBounds(10, 429, 107, 107);
		add(lbl_columna_izquierda_3);

		lbl_columna_derecha_4 = new JLabel("");
		lbl_columna_derecha_4.setIcon(
				new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png")));
		lbl_columna_derecha_4.setBounds(618, 429, 107, 107);
		add(lbl_columna_derecha_4);

		lbl_columna_derecha_5 = new JLabel("");
		lbl_columna_derecha_5.setIcon(
				new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png")));
		lbl_columna_derecha_5.setBounds(618, 302, 107, 107);
		add(lbl_columna_derecha_5);

		lbl_columna_derecha_6 = new JLabel("");
		lbl_columna_derecha_6.setIcon(
				new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png")));
		lbl_columna_derecha_6.setBounds(618, 178, 107, 107);
		add(lbl_columna_derecha_6);

		lbl_columna_derecha_7 = new JLabel("");
		lbl_columna_derecha_7.setIcon(
				new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/bloque_pregunta.png")));
		lbl_columna_derecha_7.setBounds(618, 55, 107, 107);
		add(lbl_columna_derecha_7);

		lbl_super = new JLabel("");
		lbl_super.setIcon(new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/super.png")));
		lbl_super.setBounds(236, 11, 285, 115);
		add(lbl_super);

		btn_volver = new JButton("Volver");
		btn_volver.setBackground(new Color(192, 192, 192));
		btn_volver.setBounds(297, 462, 173, 50);
		add(btn_volver);

		lbl_imagen_mario = new JLabel("");
		lbl_imagen_mario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imagen_mario.setIcon(new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/mario.png")));
		lbl_imagen_mario.setBounds(247, 462, 27, 50);
		add(lbl_imagen_mario);

		lbl_imagen_hongo = new JLabel("");
		lbl_imagen_hongo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imagen_hongo.setIcon(
				new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Sprites/Originales/mushroom.png")));
		lbl_imagen_hongo.setBounds(482, 462, 27, 50);
		add(lbl_imagen_hongo);
	}

	protected void registrar_oyente_boton_volver() {
		btn_volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador_vistas.mostrar_pantalla_inicial();
			}
		});
	}

}
