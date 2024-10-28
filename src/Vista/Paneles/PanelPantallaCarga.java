package Vista.Paneles;

import Constantes.CadenasValidacion;

import javax.swing.*;
import java.awt.*;

public class PanelPantallaCarga extends JPanel {
    private static final long serialVersionUID = 1L;

    public PanelPantallaCarga() {
        setLayout(new BorderLayout());

        JLabel gifCarga = new JLabel(new ImageIcon(getClass().getResource(CadenasValidacion.RUTA_LOADING)));
        gifCarga.setHorizontalAlignment(SwingConstants.CENTER);

        add(gifCarga, BorderLayout.CENTER);
    }
}
