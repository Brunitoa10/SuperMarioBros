package Vista.Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PanelPantallaRanking extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbl_imagen_fondo;
	private JButton btn_volver;
	private JLabel lbl_puntaje_primero;
	private JLabel lbl_puntaje_segundo;
	private JLabel lbl_puntaje_tercero;
	private JLabel lbl_puntaje_cuarto;
	private JLabel lbl_puntaje_quinto;
	
	public PanelPantallaRanking() {
		setLayout(null);
		
		cargar_fondo();
		cargar_botones();
		cargar_puntajes();
	}
	
	private void cargar_botones() {
		btn_volver = new JButton("Volver");
		btn_volver.setBackground(new Color(192, 192, 192));
		btn_volver.setBounds(323, 603, 285, 50);
		add(btn_volver);
	}

	private void cargar_fondo() {
		lbl_imagen_fondo = new JLabel("");
		lbl_imagen_fondo.setIcon(new ImageIcon(PanelPantallaRanking.class.getResource("/Recursos/Imagenes/Ranking_puntaje.png")));
		lbl_imagen_fondo.setBounds(0, 0, 973, 722);
		add(lbl_imagen_fondo);
	}

	private void cargar_puntajes() {
		
		lbl_puntaje_primero = new JLabel("0000");
		lbl_puntaje_primero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_primero.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_primero.setBackground(new Color(192, 192, 192));
		lbl_puntaje_primero.setBounds(323, 143, 285, 34);
		add(lbl_puntaje_primero);
		
		lbl_puntaje_segundo = new JLabel("0000");
		lbl_puntaje_segundo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_segundo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_segundo.setBackground(Color.LIGHT_GRAY);
		lbl_puntaje_segundo.setBounds(323, 243, 285, 34);
		add(lbl_puntaje_segundo);
		
		lbl_puntaje_tercero = new JLabel("0000");
		lbl_puntaje_tercero.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_tercero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_tercero.setBackground(Color.LIGHT_GRAY);
		lbl_puntaje_tercero.setBounds(323, 329, 285, 34);
		add(lbl_puntaje_tercero);
		
		lbl_puntaje_cuarto = new JLabel("0000");
		lbl_puntaje_cuarto.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_cuarto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_cuarto.setBackground(Color.LIGHT_GRAY);
		lbl_puntaje_cuarto.setBounds(323, 400, 285, 34);
		add(lbl_puntaje_cuarto);
		
		lbl_puntaje_quinto = new JLabel("0000");
		lbl_puntaje_quinto.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_puntaje_quinto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_puntaje_quinto.setBackground(Color.LIGHT_GRAY);
		lbl_puntaje_quinto.setBounds(323, 480, 285, 34);
		add(lbl_puntaje_quinto);
	}
}
