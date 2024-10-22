package Vista.GestorPantalla;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import Vista.Controladores.ControladorVista;

public class RegistroComandoPaneles {
	private static final Map<String, ComandoCrearPanel> registro = new HashMap<>();

    // Bloque estático para registrar los comandos
    static {
        registro.put("nivel", new ComandoCrearPanelNivel());
        registro.put("principal", new ComandoCrearPanelPrincipal());
        registro.put("ranking", new ComandoCrearPanelRanking());
        registro.put("fin", new ComandoCrearPanelFinJuego());
        registro.put("modo", new ComandoCrearPanelModoJuego());
    }

    // Método para ejecutar el comando asociado a un tipo de panel
    public static JPanel crearPanel(String tipoPanel, ControladorVista controlador) {
        ComandoCrearPanel comando = registro.get(tipoPanel);
        if (comando != null) {
            return comando.ejecutar(controlador);
        } else {
            throw new IllegalArgumentException("Tipo de panel desconocido: " + tipoPanel);
        }
    }

    // Método para registrar nuevos comandos dinámicamente
    public static void registrarComando(String tipoPanel, ComandoCrearPanel comando) {
        registro.put(tipoPanel, comando);
    }
}
