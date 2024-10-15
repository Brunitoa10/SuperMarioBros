package Vista.Paneles;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;

public class PanelPantallaPrincipal extends JPanel {

    /**
     * 
     */
    protected static final long serialVersionUID = 1L;
    protected ControladorVista controlador_vistas;
    protected JLabel imagen_fondo;
    protected JButton btn_iniciar_juego;
    protected JButton boton_puntajes;

    public PanelPantallaPrincipal(ControladorVista controlador_vistas) {
        this.controlador_vistas = controlador_vistas;
        setSize(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO);
        agregar_imagen_fondo();
        agregar_boton_iniciar();
        agregar_boton_puntaje();
    }

    protected void agregar_imagen_fondo() {
        imagen_fondo = new JLabel();
        ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource("/Recursos/imagenes/Inicio.png"));
        Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVista.PANEL_ANCHO,
                ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
        setLayout(null);
        imagen_fondo.setIcon(icono_imagen_escalado);
        imagen_fondo.setBounds(0, 5, 800, 600);
        add(imagen_fondo);
    }

    protected void agregar_boton_iniciar() {

        System.out.println("Agregue boton inicio");

        btn_iniciar_juego = new JButton("");
        btn_iniciar_juego.setBounds(300, 391, 230, 57);

        decorar_boton_iniciar();
        add(btn_iniciar_juego);

        registrar_oyente_boton_iniciar();
    }

    protected void agregar_boton_puntaje() {
        boton_puntajes = new JButton();
        // boton_puntajes.setBounds(270, 484, 380, 76);
        decorar_boton_puntajes();
        registrar_oyente_boton_puntajes();
        add(boton_puntajes);
    }

    protected void decorar_boton_iniciar() {
        System.out.println("Decorando boton inicio");
        btn_iniciar_juego.setBackground(new Color(255, 255, 255));
        transparentar_boton(btn_iniciar_juego);
    }

    protected void decorar_boton_puntajes() {
        transparentar_boton(boton_puntajes);
        boton_puntajes.setBounds(176, 487, 471, 65);
    }

    protected void registrar_oyente_boton_iniciar() {
        System.out.println("Registrando boton inicio");
        btn_iniciar_juego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton inicio apretado");
                controlador_vistas.accionar_inicio_juego();
            }
        });
    }

    protected void registrar_oyente_boton_puntajes() {
        boton_puntajes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador_vistas.accionar_pantalla_ranking();
            }
        });
    }

    protected void transparentar_boton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }
}
