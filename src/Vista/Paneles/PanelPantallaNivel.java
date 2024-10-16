package Vista.Paneles;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Vista.Controladores.ConstantesVista;
import Vista.ObserverGrafica.Observer;
import Vista.ObserverGrafica.ObserverEntidad;
import Vista.ObserverGrafica.ObserverJugador;
import java.awt.BorderLayout;
import java.awt.Color;
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

public class PanelPantallaNivel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel panelInformacion;
    private JLabel imagenFondoPanelNivel;
    private JLabel imagenFondoPanelInformacion;
    private JScrollPane panelScrollNivel;
    private JLabel labelPuntaje;
    private JLabel labelPuntajeTxt;
    private JLabel labelVida;
    private JLabel labelVidaTxt;
    private JLabel labelTiempo;
    private JLabel labelTiempoTxt;
    private ObserverEntidad observerEntidad;
    private ObserverJugador observerJugador;
    private boolean lovi=false;

    public PanelPantallaNivel() {

        setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));
        setLayout(new BorderLayout());

        agregarPanelNivelConFondoYScroll();
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());

        BoxLayout boxLayout = new BoxLayout(panelSuperior, BoxLayout.X_AXIS);
        panelSuperior.setLayout(boxLayout);
        panelSuperior.add(Box.createHorizontalGlue());

        labelVida = new JLabel("Vidas: 0");
        labelPuntaje = new JLabel("Puntaje: 0");
        labelTiempo = new JLabel("Tiempo: 0");
        panelSuperior.add(labelVida);
        panelSuperior.add(Box.createHorizontalGlue());
        panelSuperior.add(labelPuntaje);
        panelSuperior.add(Box.createHorizontalGlue());
        panelSuperior.add(labelTiempo);
        panelSuperior.add(Box.createHorizontalGlue());

        add(panelSuperior, BorderLayout.NORTH);
        // agregar_panel_informacion();
    }

    // Operaciones para ControladorVistas

    public Observer incorporarEntidad(EntidadLogica entidad_logica) {
        observerEntidad = new ObserverEntidad(entidad_logica);
        imagenFondoPanelNivel.add(observerEntidad);
        return observerEntidad;
    }

    public Observer incorporarEntidadJugador(EntidadJugador entidad_jugador) {
        observerJugador = new ObserverJugador(this, entidad_jugador);
        imagenFondoPanelNivel.add(observerJugador);
        actualizarInfoJugador(entidad_jugador);
        return observerJugador;
    }

    protected void actualizarInfoJugador(EntidadJugador jugador) {
        actualizarLabelsInformacion(jugador);
        actualizarScrollHaciaJugador(jugador);

    }

    protected void actualizarLabelsInformacion(EntidadJugador jugador) {
        labelPuntaje.setText(textoConCantidadDigitos(jugador.get_puntaje(), 5));
        labelVida.setText(textoConCantidadDigitos(jugador.get_vida(), 5));
        labelTiempo.setText(textoConCantidadDigitos(jugador.get_tiempo(), 5));
    }

    protected String textoConCantidadDigitos(int numero, int digitos) {
        String texto_autocompletado = "";
        if (enRango(numero, 0, 9)) {
            texto_autocompletado = "0000" + numero;
        } else {
            if (enRango(numero, 10, 99)) {
                texto_autocompletado = "000" + numero;
            } else {
                if (enRango(numero, 100, 999)) {
                    texto_autocompletado = "00" + numero;
                } else {
                    if (enRango(numero, 1000, 9999)) {
                        texto_autocompletado = "0" + numero;
                    } else {
                        texto_autocompletado += numero;
                    }
                }
            }
        }
        return texto_autocompletado;
    }

    protected boolean enRango(int numero, int piso, int techo) {
        boolean retorno;
        retorno = numero >= piso;
        retorno = retorno && (numero <= techo);
        return retorno;
    }

    // Operacion para observer de jugador

    public void actualizarScrollHaciaJugador(EntidadJugador jugador) {
        int posicion_jugador_x = jugador.getPosicionEnX();
        int anchoDeVentana=ConstantesVista.PANEL_ANCHO;
            panelScrollNivel.getHorizontalScrollBar().setValue(posicion_jugador_x + anchoDeVentana/2+30);

    }

    // Operaciones propias para construccion de PanelPantallaCarrera

    protected void agregarPanelNivelConFondoYScroll() {
        System.out.println("Cree imagen fondo");

        imagenFondoPanelNivel = new JLabel(
                new ImageIcon(getClass().getResource("/Recursos/Fondos/FondoLevel1.png")));
        imagenFondoPanelNivel.setLayout(null);
        imagenFondoPanelNivel.setBounds(0, 0, ConstantesVista.PANEL_NIVEL_ANCHO, ConstantesVista.PANEL_NIVEL_ALTO);
        this.add(imagenFondoPanelNivel);
        imagenFondoPanelNivel.revalidate();
        imagenFondoPanelNivel.repaint();

        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.add(imagenFondoPanelNivel, BorderLayout.CENTER);

        panelScrollNivel = new JScrollPane(panelImagen);
        panelScrollNivel.setPreferredSize(imagenFondoPanelNivel.getPreferredSize());

        panelScrollNivel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); // CAMBIAR POR
        // NEVER
        panelScrollNivel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        this.add(panelScrollNivel);

    }

    protected void agregarPanelInformacion() {
        panelInformacion = new JPanel();
        panelInformacion.setBounds(0, 0, 800, 600);
        panelInformacion.setLayout(null);
        panelInformacion
                .setPreferredSize(new Dimension(ConstantesVista.PANEL_INFORMACION_ANCHO, ConstantesVista.PANEL_ALTO));
        agregarImagenFondoPanelInformacion();
        agregarLabelsEditablesInformacion();
        setLayout(null);
        add(panelInformacion);
        labelTiempo = new JLabel("00000");
        labelTiempo.setBounds(575, 0, 150, 50);
        panelInformacion.add(labelTiempo);
        labelVida = new JLabel("00000");
        labelVida.setBounds(296, 8, 95, 22);
        panelInformacion.add(labelVida);
        labelPuntaje = new JLabel("00000");
        labelPuntaje.setBounds(62, 12, 150, 14);
        panelInformacion.add(labelPuntaje);
    }

    protected void agregarImagenFondoPanelInformacion() {
        imagenFondoPanelInformacion = new JLabel();
        ImageIcon icono_imagen = new ImageIcon("");

        Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVista.PANEL_INFORMACION_ANCHO,
                ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
        imagenFondoPanelInformacion.setIcon(icono_imagen_escalado);
        imagenFondoPanelInformacion.setBounds(0, 0, 800, 97);
        panelInformacion.add(imagenFondoPanelInformacion);
    }

    protected void agregarLabelsEditablesInformacion() {
        decorarLabelsInformacion();
    }

    protected void decorarLabelsInformacion() {
        labelPuntaje.setBounds(90, 11, 150, 14);
        labelVida.setBounds(250, 7, 95, 22);
        labelTiempo.setBounds(52, 450, 150, 50);

        labelPuntaje.setForeground(Color.WHITE);
        labelVida.setForeground(Color.WHITE);
        labelTiempo.setForeground(Color.WHITE);

        labelPuntaje.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
        labelVida.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
        labelTiempo.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
    }

    public void actualizarObserver() {
        observerJugador.actualizarObserver();
        imagenFondoPanelNivel.add(observerEntidad);
    }
}