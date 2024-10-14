package Vista.Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;
import Vista.GUI;
import Vista.ObserverGrafica.*;

public class PanelPantallaNivel extends JPanel {

    private JPanel panel_nivel;
    private JPanel panel_informacion;
    private JLabel imagen_fondo_panel_nivel;
    private JLabel imagen_fondo_panel_informacion;
    private JScrollPane panel_scroll_nivel;
    private JLabel label_puntaje;
    private JLabel label_puntaje_txt;
    private JLabel label_vida;
    private JLabel label_vida_txt;
    private JLabel label_tiempo;
    private JLabel label_tiempo_txt;
    private GUI controladorVista;

    public PanelPantallaNivel(GUI controladorVista) {
        this.controladorVista = controladorVista;
        setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));
        setLayout(new BorderLayout());
        agregar_panel_informacion();
        agregar_panel_nivel_con_fondo_y_scroll();
    }

    // Operaciones para ControladorVistas

    public Observer incorporar_entidad(EntidadLogica entidad_logica) {
        ObserverEntidad observer_entidad = new ObserverEntidad(entidad_logica);
        imagen_fondo_panel_nivel.add(observer_entidad);
        return observer_entidad;
    }

    public Observer incorporar_entidad_jugador(EntidadJugador entidad_jugador) {
        ObserverJugador observer_jugador = new ObserverJugador(this, entidad_jugador);
        imagen_fondo_panel_nivel.add(observer_jugador);
        actualizar_info_jugador(entidad_jugador);
        return observer_jugador;
    }

    public Observer incorporar_silueta(EntidadLogica entidad_logica) {
        ObserverEntidad observer_entidad = new ObserverEntidad(entidad_logica);
        imagen_fondo_panel_nivel.setIcon(new ImageIcon(getClass().getClassLoader().getResource(entidad_logica.get_sprite().get_ruta_imagen())));
        imagen_fondo_panel_nivel.setBounds(0,0, imagen_fondo_panel_nivel.getIcon().getIconWidth(), imagen_fondo_panel_nivel.getIcon().getIconHeight());
        panel_nivel.setPreferredSize(new Dimension(imagen_fondo_panel_nivel.getIcon().getIconWidth(), imagen_fondo_panel_nivel.getIcon().getIconHeight()));
        return observer_entidad;
    }

    protected void actualizar_info_jugador(EntidadJugador jugador) {
        actualizar_labels_informacion(jugador);
        actualizar_scroll_hacia_jugador(jugador);
    }

    protected void actualizar_labels_informacion(EntidadJugador jugador) {
        label_puntaje.setText( texto_con_cantidad_digitos(jugador.get_puntaje(), 5) );
        label_vida.setText( texto_con_cantidad_digitos(jugador.get_vida(), 5) );
        label_tiempo.setText( texto_con_cantidad_digitos(jugador.get_tiempo(), 5) );
    }

    protected String texto_con_cantidad_digitos(int numero, int digitos) {
        String texto_autocompletado = "";
        if (en_rango(numero, 0, 9)) {
            texto_autocompletado = "0000" + numero;
        }else {
            if (en_rango(numero, 10, 99)) {
                texto_autocompletado = "000" + numero;
            }else {
                if (en_rango(numero, 100, 999)) {
                    texto_autocompletado = "00" + numero;
                }else {
                    if(en_rango(numero, 1000, 9999)) {
                        texto_autocompletado = "0" + numero;
                    }else {
                        texto_autocompletado += numero;
                    }
                }
            }
        }
        return texto_autocompletado;
    }

    protected boolean en_rango(int numero, int piso, int techo) {
        boolean retorno;
        retorno = numero >= piso;
        retorno = retorno && (numero <= techo);
        return retorno;
    }

    // Operacion para observer de jugador

    protected void actualizar_scroll_hacia_jugador(EntidadJugador jugador) {
        // To DO
        //panel_scroll_carrera.getVerticalScrollBar().setValue( panel_scroll_carrera.getVerticalScrollBar().getValue() + jugador.get_velocidad() );
    }

    // Operaciones propias para construccion de PanelPantallaCarrera

    protected void agregar_panel_nivel_con_fondo_y_scroll() {
        imagen_fondo_panel_nivel = new JLabel();
        imagen_fondo_panel_nivel.setLayout(null);
        imagen_fondo_panel_nivel.setBounds(0,0, ConstantesVista.PANEL_NIVEL_ANCHO, ConstantesVista.PANEL_ALTO);

        panel_nivel = new JPanel(null);
        panel_nivel.setPreferredSize(new Dimension(ConstantesVista.PANEL_NIVEL_ANCHO, ConstantesVista.PANEL_ALTO));
        panel_nivel.add(imagen_fondo_panel_nivel);

        panel_scroll_nivel = new JScrollPane(panel_nivel);
        panel_scroll_nivel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel_scroll_nivel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel_scroll_nivel.setBounds(0, 0, ConstantesVista.PANEL_NIVEL_ANCHO, ConstantesVista.PANEL_ALTO);

        add(panel_scroll_nivel, BorderLayout.CENTER);
    }

    protected void agregar_panel_informacion() {
        panel_informacion = new JPanel();
        panel_informacion.setLayout(null);
        panel_informacion.setPreferredSize(new Dimension(ConstantesVista.PANEL_INFORMACION_ANCHO, ConstantesVista.PANEL_ALTO));
        agregar_imagen_fondo_panel_informacion();
        agregar_labels_editables_informacion();
        add(panel_informacion, BorderLayout.EAST);
    }

    protected void agregar_imagen_fondo_panel_informacion() {
        imagen_fondo_panel_informacion = new JLabel();
        ImageIcon icono_imagen = new ImageIcon("");
        Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVista.PANEL_INFORMACION_ANCHO, ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
        imagen_fondo_panel_informacion.setIcon(icono_imagen_escalado);
        imagen_fondo_panel_informacion.setBounds(0,0, ConstantesVista.PANEL_INFORMACION_ANCHO, ConstantesVista.PANEL_ALTO);
        panel_informacion.add(imagen_fondo_panel_informacion);
    }

    protected void agregar_labels_editables_informacion() {
        label_puntaje = new JLabel("00000");
        label_vida = new JLabel("00000");
        label_tiempo = new JLabel("00000");
        decorar_labels_informacion();
        imagen_fondo_panel_informacion.add(label_puntaje);
        imagen_fondo_panel_informacion.add(label_vida);
        imagen_fondo_panel_informacion.add(label_tiempo);
    }

    protected void decorar_labels_informacion() {
        label_puntaje.setBounds(90, 11, 150, 14);
        label_vida.setBounds(250, 7, 95, 22);
        label_tiempo.setBounds(52, 450, 150, 50);

        label_puntaje.setForeground(Color.WHITE);
        label_vida.setForeground(Color.WHITE);
        label_tiempo.setForeground(Color.WHITE);

        label_puntaje.setFont(new Font(label_puntaje.getFont().getName(), Font.BOLD, 24));
        label_vida.setFont(new Font(label_puntaje.getFont().getName(), Font.BOLD, 24));
        label_tiempo.setFont(new Font(label_puntaje.getFont().getName(), Font.BOLD, 24));
    }
}