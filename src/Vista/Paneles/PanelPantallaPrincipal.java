package Vista.Paneles;

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

    private ControladorVista controlador_vistas;
    private JLabel imagen_fondo;
    private JButton boton_iniciar;
    private JButton boton_puntajes;

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
        imagen_fondo.setIcon(icono_imagen_escalado);
        imagen_fondo.setBounds(10, 28, ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO);
        add(imagen_fondo);
    }

    protected void agregar_boton_iniciar() {
        boton_iniciar = new JButton();
        decorar_boton_iniciar();
        registrar_oyente_boton_iniciar();
        add(boton_iniciar);
    }

    protected void agregar_boton_puntaje() {
        boton_puntajes = new JButton();
        boton_puntajes.setBounds(0, 0, 800, 600);
        decorar_boton_puntajes();
        registrar_oyente_boton_puntajes();
        add(boton_puntajes);
    }

    protected void decorar_boton_iniciar() {
        transparentar_boton(boton_iniciar);
    }

    protected void decorar_boton_puntajes() {
        transparentar_boton(boton_puntajes);
    }

    protected void registrar_oyente_boton_iniciar() {
        boton_iniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador_vistas.accionar_inicio_juego();
            }
        });
    }

    protected void registrar_oyente_boton_puntajes() {
        boton_puntajes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador_vistas.accionar_pantalla_modo_juego();
            }
        });
    }

    protected void transparentar_boton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }
}
