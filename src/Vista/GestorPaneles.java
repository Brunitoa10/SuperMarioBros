package Vista;

import javax.swing.JPanel;

public class GestorPaneles {
	protected VentanaManager ventana;
	protected HistorialPaneles historialPaneles;

    public GestorPaneles(VentanaManager ventana, HistorialPaneles historialPaneles) {
        this.ventana = ventana;
       this.historialPaneles = historialPaneles;
    }

    public void mostrarPantalla(JPanel nuevaPantalla) {
    	historialPaneles.push(nuevaPantalla);
        ventana.setContenido(nuevaPantalla);
        refrescar();
    }

    public void refrescar() {
        ventana.refrescar();
    }
}
