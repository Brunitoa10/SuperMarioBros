package Vista.Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Vista.Controladores.ConstantesVista;
import Vista.ObserverGrafica.Observer;
import Vista.ObserverGrafica.ObserverEntidad;
import Vista.ObserverGrafica.ObserverJugador;

public class PanelPantallaNivel extends JPanel {

    /**
     *
     */
    protected static final long serialVersionUID = 1L;
    protected JPanel panel_informacion;
    protected JLabel imagen_fondo_panel_nivel;
    protected JLabel imagen_fondo_panel_informacion;
    protected JScrollPane panel_scroll_nivel;
    protected JLabel label_puntaje;
    protected JLabel label_puntaje_txt;
    protected JLabel label_vida;
    protected JLabel label_vida_txt;
    protected JLabel label_tiempo;
    protected JLabel label_tiempo_txt;
    protected ObserverEntidad observer_entidad;
    protected ObserverJugador observer_jugador;

    public PanelPantallaNivel() {

        setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));
        setLayout(new BorderLayout());

        agregar_panel_nivel_con_fondo_y_scroll();
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(null);

        BoxLayout boxLayout = new BoxLayout(panelSuperior, BoxLayout.X_AXIS);
        panelSuperior.setLayout(boxLayout);

        Component horizontalGlue_4 = Box.createHorizontalGlue();
        panelSuperior.add(horizontalGlue_4);

        JLabel lbl_vida_txt = new JLabel("Vida");
        panelSuperior.add(lbl_vida_txt);
        Component horizontalGlue = Box.createHorizontalGlue();
        horizontalGlue.setBounds(0, 7, 154, 12);
        panelSuperior.add(horizontalGlue);

        label_vida = new JLabel("Vidas: 0");
        label_vida.setBounds(166, 0, 76, 14);
        label_puntaje = new JLabel("Puntaje: 0");
        label_puntaje.setBounds(346, 0, 50, 14);
        label_tiempo = new JLabel("Tiempo: 0");
        label_tiempo.setBounds(550, 0, 47, 14);
        panelSuperior.add(label_vida);
        Component horizontalGlue_1 = Box.createHorizontalGlue();
        horizontalGlue_1.setBounds(192, 7, 154, 12);
        panelSuperior.add(horizontalGlue_1);

        JLabel lbl_puntaje_txt = new JLabel("Puntaje");
        panelSuperior.add(lbl_puntaje_txt);

        Component horizontalGlue_1_1 = Box.createHorizontalGlue();
        panelSuperior.add(horizontalGlue_1_1);
        panelSuperior.add(label_puntaje);
        Component horizontalGlue_2 = Box.createHorizontalGlue();
        horizontalGlue_2.setBounds(396, 7, 154, 12);
        panelSuperior.add(horizontalGlue_2);

        JLabel lbl_tiempo_txt = new JLabel("Tiempo");
        panelSuperior.add(lbl_tiempo_txt);

        Component horizontalGlue_1_1_1 = Box.createHorizontalGlue();
        panelSuperior.add(horizontalGlue_1_1_1);
        panelSuperior.add(label_tiempo);
        Component horizontalGlue_3 = Box.createHorizontalGlue();
        horizontalGlue_3.setBounds(597, 7, 154, 12);
        panelSuperior.add(horizontalGlue_3);

        add(panelSuperior, BorderLayout.NORTH);
        // agregar_panel_informacion();
    }

    // Operaciones para ControladorVistas

    public Observer incorporar_entidad(EntidadLogica entidad_logica) {
        observer_entidad = new ObserverEntidad(entidad_logica);
        imagen_fondo_panel_nivel.add(observer_entidad);
        return observer_entidad;
    }

    public Observer incorporar_entidad_jugador(EntidadJugador entidad_jugador) {
        observer_jugador = new ObserverJugador(this, entidad_jugador);
        imagen_fondo_panel_nivel.add(observer_jugador);
        actualizar_info_jugador(entidad_jugador);
        return observer_jugador;
    }

    protected void actualizar_info_jugador(EntidadJugador jugador) {
        actualizar_labels_informacion(jugador);
        actualizar_scroll_hacia_jugador(jugador);

    }

    protected void actualizar_labels_informacion(EntidadJugador jugador) {
        label_puntaje.setText(texto_con_cantidad_digitos(jugador.get_puntaje(), 5));
        label_vida.setText(texto_con_cantidad_digitos(jugador.get_vida(), 5));
        label_tiempo.setText(texto_con_cantidad_digitos(jugador.get_tiempo(), 5));
    }

    protected String texto_con_cantidad_digitos(int numero, int digitos) {
        /*
         * String texto_autocompletado = "";
         * if (en_rango(numero, 0, 9)) {
         * texto_autocompletado = "0000" + numero;
         * } else {
         * if (en_rango(numero, 10, 99)) {
         * texto_autocompletado = "000" + numero;
         * } else {
         * if (en_rango(numero, 100, 999)) {
         * texto_autocompletado = "00" + numero;
         * } else {
         * if (en_rango(numero, 1000, 9999)) {
         * texto_autocompletado = "0" + numero;
         * } else {
         * texto_autocompletado += numero;
         * }
         * }
         * }
         * }
         * return texto_autocompletado;
         */
        return String.format("%0" + digitos + "d", numero);
    }

    /*
     * protected boolean en_rango(int numero, int piso, int techo) {
     * return (numero >= piso)&& (numero <= techo);
     * }
     */

    // Operacion para observer de jugador

    public void actualizar_scroll_hacia_jugador(EntidadJugador jugador) {

        int posicion_jugador_x = jugador.get_posicion_x();

        // Obtener el ancho de la ventana visible
        int pantallaAncho = panel_scroll_nivel.getViewport().getWidth();
        // Obtener la posición actual del scroll
        int posicionDelScroll = panel_scroll_nivel.getHorizontalScrollBar().getValue();
        // Obtener el máximo del scroll
        int topeDelScroll = panel_scroll_nivel.getHorizontalScrollBar().getMaximum();

        // Si el jugador se está acercando al borde derecho de la ventana visible
        if (posicion_jugador_x > posicionDelScroll + pantallaAncho - 100 && posicionDelScroll < topeDelScroll) {
            // Ajustamos la posición del scroll hacia la derecha
            panel_scroll_nivel.getHorizontalScrollBar()
                    .setValue(Math.min(posicion_jugador_x - pantallaAncho + 100, topeDelScroll));
        }

        // Si el jugador se está acercando al borde izquierdo de la ventana visible
        if (posicion_jugador_x < posicionDelScroll + 100 && posicionDelScroll > 0) {
            // Ajustamos la posición del scroll hacia la izquierda
            panel_scroll_nivel.getHorizontalScrollBar().setValue(Math.max(posicion_jugador_x - 100, 0));
        }
    }

    // Operaciones propias para construccion de PanelPantallaCarrera

    protected void agregar_panel_nivel_con_fondo_y_scroll() {
        // Cargar la imagen de fondo
        ImageIcon imagenIcon = new ImageIcon(getClass().getResource("/Recursos/Fondos/FondoLevel1.png"));
        imagen_fondo_panel_nivel = new JLabel(imagenIcon);

        // Obtener las dimensiones de la imagen
        int anchoImagen = imagenIcon.getIconWidth();
        int altoImagen = imagenIcon.getIconHeight();

        // Establecer las dimensiones del panel según la imagen
        imagen_fondo_panel_nivel.setLayout(null);
        imagen_fondo_panel_nivel.setBounds(0, 0, anchoImagen, altoImagen);

        // Crear un JPanel que contenga la imagen
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.add(imagen_fondo_panel_nivel, BorderLayout.CENTER);

        // Establecer el JScrollPane con las dimensiones de la imagen
        panel_scroll_nivel = new JScrollPane(panelImagen);
        panel_scroll_nivel.setPreferredSize(new Dimension(anchoImagen, altoImagen));

        panel_scroll_nivel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // con always
                                                                                                         // se ve la
                                                                                                         // barra
        panel_scroll_nivel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        // Agregar el JScrollPane al panel
        this.add(panel_scroll_nivel);

    }

    protected void agregar_panel_informacion() {
        panel_informacion = new JPanel();
        panel_informacion.setBounds(0, 0, 800, 600);
        panel_informacion.setLayout(null);
        panel_informacion
                .setPreferredSize(new Dimension(ConstantesVista.PANEL_INFORMACION_ANCHO, ConstantesVista.PANEL_ALTO));
        agregar_imagen_fondo_panel_informacion();
        agregar_labels_editables_informacion();
        setLayout(null);
        add(panel_informacion);
        label_tiempo = new JLabel("00000");
        label_tiempo.setBounds(575, 0, 150, 50);
        panel_informacion.add(label_tiempo);
        label_vida = new JLabel("00000");
        label_vida.setBounds(296, 8, 95, 22);
        panel_informacion.add(label_vida);
        label_puntaje = new JLabel("00000");
        label_puntaje.setBounds(62, 12, 150, 14);
        panel_informacion.add(label_puntaje);
    }

    protected void agregar_imagen_fondo_panel_informacion() {
        imagen_fondo_panel_informacion = new JLabel();
        ImageIcon icono_imagen = new ImageIcon("");

        Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVista.PANEL_INFORMACION_ANCHO,
                ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
        imagen_fondo_panel_informacion.setIcon(icono_imagen_escalado);
        imagen_fondo_panel_informacion.setBounds(0, 0, 800, 97);
        panel_informacion.add(imagen_fondo_panel_informacion);
    }

    protected void agregar_labels_editables_informacion() {
        decorar_labels_informacion();
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

    public void actualizarObserver() {
        observer_jugador.actualizar_observer();
        imagen_fondo_panel_nivel.add(observer_entidad);
    }
}