package Vista.Paneles;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Fabricas.Sprite;
import Logica.Juego;
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
    private Image fondo_panel_nivel;
    private BufferedImage marioImage;
    private int marioX = 100;
    private int marioY = 495;
    private JPanel panelContenido;
    private JLabel lbl_puntaje;
    private JLabel lbl_vida;
    private JScrollPane scrollPane;

    public PanelPantallaNivel(int nivel, ControladorVista controlador_vistas) {
       
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Crear el panel que contendrá el contenido desplazable
        panelContenido = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar imagen de fondo
                if (fondo_panel_nivel != null) {
                    g.drawImage(fondo_panel_nivel, 0, 0, fondo_panel_nivel.getWidth(null), fondo_panel_nivel.getHeight(null), this);
                }
                // Dibujar sprites
                List<Sprite> spriteLinkedList = Juego.SingletonInstancia().getSprite();
                for (Sprite sprite : spriteLinkedList) {
                    try {
                        Image imagen = ImageIO.read(new File(sprite.get_ruta_imagen()));
                        g.drawImage(imagen, sprite.getPosicionX(), sprite.getPosicionY(), null);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        
        panelContenido.setPreferredSize(new Dimension(ConstantesVista.PANEL_NIVEL_ANCHO, ConstantesVista.PANEL_NIVEL_ALTO));

        cargarRecursos(nivel);
        // Ajustar el tamaño del panel de contenido según el tamaño de la imagen de fondo
        if (fondo_panel_nivel != null) {
            panelContenido.setPreferredSize(new Dimension(fondo_panel_nivel.getWidth(null), fondo_panel_nivel.getHeight(null)));
        }
        
        agregar_panel_informacion(panelContenido);
        crear_scroll();      
       
    }
    
    private void crear_scroll() {
    	scrollPane = new JScrollPane(panelContenido);      
    	scrollPane.setPreferredSize(new Dimension(813, 607));

        
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0)); 

        
        add(scrollPane);
	}

	private void crear_lbl_informacion() {
		
		lbl_puntaje_txt = new JLabel("Puntaje");
	    lbl_puntaje_txt.setBounds(38, 11, 44, 14);
	    panelContenido.add(lbl_puntaje_txt);
	    lbl_puntaje_txt.setFont(new Font("Tahoma", Font.BOLD, 11));
	    lbl_puntaje_txt.setHorizontalAlignment(SwingConstants.CENTER);
	       
    	lbl_puntaje = new JLabel("00000");
        lbl_puntaje.setBounds(90, 11, 150, 14);
        panelContenido.add(lbl_puntaje);
        lbl_puntaje.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_puntaje.setFont(new Font("Tahoma", Font.PLAIN, 11));
        
        lbl_Vida_txt = new JLabel("Vida");
        lbl_Vida_txt.setBounds(250, 7, 95, 22);
        panelContenido.add(lbl_Vida_txt);
        lbl_Vida_txt.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_Vida_txt.setHorizontalAlignment(SwingConstants.CENTER);
        
        lbl_vida = new JLabel("0");
        lbl_vida.setBounds(320, 11, 150, 14);
        panelContenido.add(lbl_vida);
        lbl_vida.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_vida.setFont(new Font("Tahoma", Font.PLAIN, 11));
        
        lbl_tiempo_txt = new JLabel("Tiempo");
        lbl_tiempo_txt.setBounds(498, 7, 95, 22);
        panelContenido.add(lbl_tiempo_txt);
        lbl_tiempo_txt.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_tiempo_txt.setHorizontalAlignment(SwingConstants.CENTER);
        
        lbl_tiempo = new JLabel("00000");
        lbl_tiempo.setBounds(573, 11, 150, 14);
        panelContenido.add(lbl_tiempo);
        lbl_tiempo.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_tiempo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        
        
    }
    private void cargarRecursos(int nivel) {
        // Cargar imagen de fondo
        try {
            fondo_panel_nivel = ImageIO.read(new File("src/Recursos/Fondos/" + nivel + "_Nivel.png"));
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

    // Operaciones para ControladorVistas
    public Observer incorporar_entidad(EntidadLogica entidad_logica) {
        ObserverEntidad observer_entidad = new ObserverEntidad(entidad_logica);
        // panel_nivel_fondo.add(observer_entidad);
        return observer_entidad;
    }

    public Observer incorporar_entidad_jugador(EntidadJugador entidad_jugador) {
        ObserverJugador observer_jugador = new ObserverJugador(this, entidad_jugador);
        // panel_nivel_fondo.add(observer_jugador);
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

    protected void agregar_panel_informacion(JPanel panelContenido) {
       panelContenido.setLayout(null);
       crear_lbl_informacion();
    }

    public void cambiar_fondo(int nivel) {
        cargarRecursos(nivel); // Volver a cargar el fondo si cambias de nivel
        repaint(); // Redibujar el panel
    }
}
