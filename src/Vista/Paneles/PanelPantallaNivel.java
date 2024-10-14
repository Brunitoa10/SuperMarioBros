package Vista.Paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
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
    private static final long serialVersionUID = 1L;
    private JLabel lbl_puntaje_txt;
    private JLabel lbl_Vida_txt;
    private JLabel lbl_tiempo_txt;
    private JLabel lbl_tiempo;
    private Image background; // Imagen de fondo
    private BufferedImage marioImage; // Imagen de Mario
    private int marioX = 100; // Posición X de Mario
    private int marioY = 100; // Posición Y de Mario

    public PanelPantallaNivel(int nivel, ControladorVista controlador_vistas) {
        setPreferredSize(new Dimension(813, 607));
        cargarRecursos(nivel); // Cargar imágenes
        agregar_panel_informacion();
        agregar_panel_carrera_con_fondo_y_scroll();
    }

    private void cargarRecursos(int nivel) {
        // Cargar imagen de fondo
        try {
            background = ImageIO.read(new File("src/Recursos/Fondos/" + nivel + "_Nivel.png"));
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen de fondo: " + e.getMessage());
        }

        // Cargar sprite de Mario
        try {
            marioImage = ImageIO
                    .read(new File("src/Recursos/Sprites/Originales/Jugador/PNGMario/StandingMarioRigth.png"));
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen de Mario: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar imagen de fondo
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

        // Dibujar sprite de Mario
        if (marioImage != null) {
            g.drawImage(marioImage, marioX, marioY, this);
        }
    }

    // Operaciones para ControladorVistas

    public Observer incorporar_entidad(EntidadLogica entidad_logica) {
        ObserverEntidad observer_entidad = new ObserverEntidad(entidad_logica);
        //panel_nivel_fondo.add(observer_entidad);
        return observer_entidad;
    }

    public Observer incorporar_entidad_jugador(EntidadJugador entidad_jugador) {
        ObserverJugador observer_jugador = new ObserverJugador(this, entidad_jugador);
        //panel_nivel_fondo.add(observer_jugador);
        actualizar_info_jugador(entidad_jugador);
        return observer_jugador;
    }

    protected void actualizar_info_jugador(EntidadJugador jugador) {
        actualizar_labels_informacion(jugador);
    }

    protected void actualizar_labels_informacion(EntidadJugador jugador) {
        lbl_puntaje_txt.setText(texto_con_cantidad_digitos(jugador.get_puntaje(), 5));
        lbl_Vida_txt.setText(texto_con_cantidad_digitos(jugador.get_vida(), 5));
        lbl_tiempo_txt.setText(texto_con_cantidad_digitos(jugador.get_tiempo(), 5));
    }

    protected String texto_con_cantidad_digitos(int numero, int digitos) {
        String texto_autocompletado = String.format("%0" + digitos + "d", numero);
        return texto_autocompletado;
    }

    protected void agregar_panel_informacion() {
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

    protected void agregar_panel_carrera_con_fondo_y_scroll() {
    }

    public void cambiar_fondo(int nivel) {
        cargarRecursos(nivel); // Volver a cargar el fondo si cambias de nivel
        repaint(); // Redibujar el panel
    }
}
