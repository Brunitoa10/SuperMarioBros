package Vista;

import Constantes.ConstantesVista;

public class AdaptadorPosicionPixel {
    public static int transformarX(int x) {
        return ConstantesVista.PANEL_NIVEL_ANCHO + x;
    }

    // Las coordenadas gráficas se miden de arriba hacia abajo
    public static int transformarY(int y) {
        return y;
    }

}
