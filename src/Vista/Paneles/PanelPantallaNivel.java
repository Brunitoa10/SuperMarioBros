package Vista.Paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
    private static final long serialVersionUID = 1L;
    private JLabel imagen_fondo_panel_nivel;
    private JLabel lbl_puntaje_txt;
    private JLabel lbl_Vida_txt;
    private JLabel lbl_tiempo_txt;
    private JLabel lbl_tiempo;

    public PanelPantallaNivel() {
        setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));
        agregar_panel_informacion();
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

    // Operaciones propias para construccion de PanelPantallaCarrera

    protected void agregar_panel_informacion() {
        // agregar_imagen_fondo_panel_informacion();

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
        agregar_labels_editables_informacion();
    }

    /*
     * protected void agregar_imagen_fondo_panel_informacion() {
     * ImageIcon icono_imagen = new ImageIcon(
     * this.getClass().getResource("/Recursos/imagenes/pantalla-informacion.png"));
     * Image imagen_escalada =
     * icono_imagen.getImage().getScaledInstance(ConstantesVista.
     * PANEL_INFORMACION_ANCHO,
     * ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
     * Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
     * }
     */

    protected void agregar_labels_editables_informacion() {
        decorar_labels_informacion();
    }

    protected void decorar_labels_informacion() {
    }
}
