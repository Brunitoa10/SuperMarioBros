package Vista;

import Constantes.ConstantesVista;

public class AdaptadorPosicionPixel {
    public static int transformarX(int x) {
        // return x;
        return ConstantesVista.PANEL_NIVEL_ANCHO + x;
    }

    // Las coordenadas gráficas se miden de arriba hacia abajo
    public static int transformarY(int y) {
        // return ConstantesVista.PANEL_ALTO - y;
        return y;
    }

}
