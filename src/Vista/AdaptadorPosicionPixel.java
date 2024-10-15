package Vista;

import Vista.Controladores.ConstantesVista;

public class AdaptadorPosicionPixel {
    public static int transformar_x(int x) {
        return ConstantesVista.PANEL_NIVEL_ANCHO + x;
    }

    // Las coordenadas gráficas se miden de arriba hacia abajo
    public static int transformar_y(int y) {
        return y;
    }

}
