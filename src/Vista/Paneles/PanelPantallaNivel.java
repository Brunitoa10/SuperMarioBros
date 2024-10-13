package Vista.Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;
import Vista.ObserverGrafica.Observer;
import Vista.ObserverGrafica.ObserverEntidad;
import Vista.ObserverGrafica.ObserverJugador;

public class PanelPantallaNivel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JLabel lbl_puntaje_txt;
    private JLabel lbl_Vida_txt;
    private JLabel lbl_tiempo_txt;
    private JLabel lbl_tiempo;
    private JLabel imagen_fondo_panel_nivel;
    private Imagenfondo fondo;
    JPanel panel_nivel_fondo;
    JScrollPane scroll_Panel_nivel;

    public PanelPantallaNivel(int nivel, ControladorVista controlador_vistas) {
        setPreferredSize(new Dimension(813, 607));

        agregar_panel_informacion();
        agregar_panel_carrera_con_fondo_y_scroll();
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

    protected void actualizar_info_jugador(EntidadJugador jugador) {
        actualizar_labels_informacion(jugador);
    }

    protected void actualizar_labels_informacion(EntidadJugador jugador) {
        lbl_puntaje_txt.setText(texto_con_cantidad_digitos(jugador.get_puntaje(), 5));
        lbl_Vida_txt.setText(texto_con_cantidad_digitos(jugador.get_tiempo(), 5));
        lbl_tiempo_txt.setText(texto_con_cantidad_digitos(jugador.get_vida(), 5)); // Pedir a nivel?
    }

    protected String texto_con_cantidad_digitos(int numero, int digitos) {
        String texto_autocompletado = "";
        if (en_rango(numero, 0, 9)) {
            texto_autocompletado = "0000" + numero;
        } else {
            if (en_rango(numero, 10, 99)) {
                texto_autocompletado = "000" + numero;
            } else {
                if (en_rango(numero, 100, 999)) {
                    texto_autocompletado = "00" + numero;
                } else {
                    if (en_rango(numero, 1000, 9999)) {
                        texto_autocompletado = "0" + numero;
                    } else {
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
        // panel_scroll_nivel.getVerticalScrollBar().setValue(
        // panel_scroll_nivel.getVerticalScrollBar().getValue() +
        // jugador.get_velocidad() );
    }
    // Operaciones propias para construccion de PanelPantallaCarrera

    protected void agregar_panel_informacion() {

        setLayout(null);
        setLayout(null);

        lbl_puntaje_txt = new JLabel("Puntaje");
        lbl_puntaje_txt.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_puntaje_txt.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_puntaje_txt.setBounds(10, 11, 95, 22);
        add(lbl_puntaje_txt);

        lbl_Vida_txt = new JLabel("Vida");
        lbl_Vida_txt.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_Vida_txt.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Vida_txt.setBounds(285, 11, 95, 22);
        add(lbl_Vida_txt);

        lbl_tiempo_txt = new JLabel("Tiempo");
        lbl_tiempo_txt.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_tiempo_txt.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_tiempo_txt.setBounds(532, 11, 95, 22);
        add(lbl_tiempo_txt);

        JLabel lbl_puntaje = new JLabel("00000");
        lbl_puntaje.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_puntaje.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbl_puntaje.setBounds(97, 15, 150, 14);
        add(lbl_puntaje);

        JLabel lbl_vida = new JLabel("0");
        lbl_vida.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_vida.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbl_vida.setBounds(354, 15, 150, 14);
        add(lbl_vida);

        lbl_tiempo = new JLabel("00000");
        lbl_tiempo.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_tiempo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lbl_tiempo.setBounds(607, 15, 150, 14);
        add(lbl_tiempo);
    }

    // Operaciones propias para construccion de PanelPantallaCarrera

    protected void agregar_panel_carrera_con_fondo_y_scroll() {
    	 // Configurar el JLabel con la imagen de fondo
        imagen_fondo_panel_nivel = new JLabel();
        imagen_fondo_panel_nivel.setIcon(new ImageIcon(PanelPantallaNivel.class.getResource("/Recursos/Fondos/1_Nivel.png")));
        imagen_fondo_panel_nivel.setLayout(null);
        
        // Ajustar el tamaño preferido para el fondo
        imagen_fondo_panel_nivel.setPreferredSize(new Dimension(1600, ConstantesVista.PANEL_ALTO)); // Aumenta el ancho según necesites

        // Crear un panel para el fondo
        panel_nivel_fondo = new JPanel();
        panel_nivel_fondo.setLayout(null);
        
        // Establecer un tamaño preferido para el panel
        panel_nivel_fondo.setPreferredSize(new Dimension(1600, ConstantesVista.PANEL_ALTO)); // Aumenta el ancho aquí también
        panel_nivel_fondo.add(imagen_fondo_panel_nivel);

        // Configurar el JScrollPane
        scroll_Panel_nivel = new JScrollPane(panel_nivel_fondo);
        scroll_Panel_nivel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Cambia a ALWAYS para ver el scrollbar siempre
        scroll_Panel_nivel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Desactivar el scrollbar vertical
        scroll_Panel_nivel.setBounds(0, 40, 800, 560); // Ajustar altura para el espacio de la información
        
        // Establecer bordes para pruebas (opcional)
        panel_nivel_fondo.setBorder(BorderFactory.createLineBorder(Color.RED));
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PanelPantallaNivel.class.getResource("/Recursos/Fondos/1_Nivel.png")));
        lblNewLabel.setBounds(0, 0, 798, 548);
        panel_nivel_fondo.add(lblNewLabel);
        scroll_Panel_nivel.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        // Agregar el JScrollPane al panel principal
        add(scroll_Panel_nivel);
    }

    // Clase auxiliar para poder hacer el fondo

    @SuppressWarnings("unused")
    private class Imagenfondo extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 4180857344385898634L;

        private Image imagen;
        private int nivelActual = 1;

        public Imagenfondo(int nivel) {
            this.nivelActual = nivel;
            cambiarFondo(nivelActual);
        }

        public void cambiarFondo(int nivel) {
            this.nivelActual = nivel;
            String rutaImagen = "/Recursos/Fondos/" + nivelActual + "_Nivel.png";
            imagen = new ImageIcon(getClass().getResource(rutaImagen)).getImage();

            if (imagen == null) {
                System.out.println("Error: Imagen no encontrada en la ruta: " + rutaImagen);
            } else {
                System.out.println("Imagen cargada correctamente: " + rutaImagen);
            }
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }

    }

    public void cambiar_fondo(int nivel) {
        fondo.cambiarFondo(nivel);
        fondo.repaint();
    }
}
