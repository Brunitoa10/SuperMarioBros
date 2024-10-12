package Vista.Paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
    private JLabel imagen_fondo_panel_nivel;
    private JLabel label_puntaje;
    private JLabel lbl_vida;
    private JLabel label_vida;
    private JLabel lbl_puntaje_txt;
    private JLabel lbl_Vida_txt;
    private JLabel lbl_tiempo_txt;

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
        label_puntaje.setText(texto_con_cantidad_digitos(jugador.get_puntaje(), 5));
        lbl_vida.setText(texto_con_cantidad_digitos(jugador.get_tiempo(), 5));
        label_vida.setText(texto_con_cantidad_digitos(jugador.get_vida(), 5)); // Pedir a nivel?
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
        agregar_imagen_fondo_panel_informacion();
        agregar_labels_editables_informacion();
        setLayout(null);
        label_puntaje = new JLabel("00000");
        label_puntaje.setHorizontalAlignment(SwingConstants.CENTER);
        label_puntaje.setBounds(115, 11, 130, 22);
        add(label_puntaje);

        lbl_puntaje_txt = new JLabel("Puntaje");
        lbl_puntaje_txt.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_puntaje_txt.setBounds(10, 11, 95, 22);
        add(lbl_puntaje_txt);

        lbl_Vida_txt = new JLabel("Vida");
        lbl_Vida_txt.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Vida_txt.setBounds(285, 11, 95, 22);
        add(lbl_Vida_txt);
        lbl_vida = new JLabel("0");
        lbl_vida.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_vida.setBounds(390, 11, 95, 22);
        add(lbl_vida);
        label_vida = new JLabel("00000");
        label_vida.setBounds(653, 11, 113, 34);
        add(label_vida);

        lbl_tiempo_txt = new JLabel("tiempo");
        lbl_tiempo_txt.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_tiempo_txt.setBounds(537, 17, 95, 22);
        add(lbl_tiempo_txt);
    }

    protected void agregar_imagen_fondo_panel_informacion() {
        ImageIcon icono_imagen = new ImageIcon(
                this.getClass().getResource("/Recursos/imagenes/pantalla-informacion.png"));
        Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVista.PANEL_INFORMACION_ANCHO,
                ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
    }

    protected void agregar_labels_editables_informacion() {
        decorar_labels_informacion();
    }

    protected void decorar_labels_informacion() {
        label_puntaje.setBounds(49, 120, 150, 50);
        lbl_vida.setBounds(54, 290, 150, 50);
        label_vida.setBounds(52, 450, 150, 50);

        label_puntaje.setForeground(Color.WHITE);
        lbl_vida.setForeground(Color.WHITE);
        label_vida.setForeground(Color.WHITE);

        label_puntaje.setFont(new Font(label_puntaje.getFont().getName(), Font.BOLD, 24));
        lbl_vida.setFont(new Font(label_puntaje.getFont().getName(), Font.BOLD, 24));
        label_vida.setFont(new Font(label_puntaje.getFont().getName(), Font.BOLD, 24));
    }
}
