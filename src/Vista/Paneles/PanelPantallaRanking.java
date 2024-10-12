package Vista.Paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;

public class PanelPantallaRanking extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_puntaje_primero;
	private JTextField text_puntaje_segundo;
	private JTextField text_puntaje_tercero;
	private JTextField text_puntaje_cuarto;
	private JTextField text_puntaje_quinto;
	private JLabel lbl_imagen_fondo;
	private JButton btn_volver;
	
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
		text_puntaje_primero = new JTextField();
		text_puntaje_primero.setText("0000");
		text_puntaje_primero.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_puntaje_primero.setBounds(323, 146, 285, 31);
		add(text_puntaje_primero);
		text_puntaje_primero.setColumns(10);
		
		text_puntaje_segundo = new JTextField();
		text_puntaje_segundo.setText("0000");
		text_puntaje_segundo.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_puntaje_segundo.setColumns(10);
		text_puntaje_segundo.setBounds(323, 209, 285, 31);
		add(text_puntaje_segundo);
		
		text_puntaje_tercero = new JTextField();
		text_puntaje_tercero.setText("0000");
		text_puntaje_tercero.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_puntaje_tercero.setColumns(10);
		text_puntaje_tercero.setBounds(323, 288, 285, 31);
		add(text_puntaje_tercero);
		
		text_puntaje_cuarto = new JTextField();
		text_puntaje_cuarto.setText("0000");
		text_puntaje_cuarto.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_puntaje_cuarto.setColumns(10);
		text_puntaje_cuarto.setBounds(323, 374, 285, 31);
		add(text_puntaje_cuarto);
		
		text_puntaje_quinto = new JTextField();
		text_puntaje_quinto.setText("0000");
		text_puntaje_quinto.setFont(new Font("Tahoma", Font.BOLD, 11));
		text_puntaje_quinto.setColumns(10);
		text_puntaje_quinto.setBounds(323, 433, 285, 31);
		add(text_puntaje_quinto);
	}
}
