package Generador.GestorSonido;

import java.util.HashMap;
import java.util.Map;

public class SonidoFactory {
    protected static final Map<String, CreadorSonido> registroSonidos = new HashMap<>();

    // Bloque estático para registrar los tipos de sonido
    static {
        System.out.println("Me cree");
        registroSonidos.put("salto", () -> new SonidoSalto("src/Recursos/Sonidos/Jump.wav"));
        // registroSonidos.put("MusicaNivel", () -> new
        // SonidoNivel("Recursos/Sonidos/Song.wav"));
        // Puedes registrar otros tipos de sonido aquí, por ejemplo:
        // registroSonidos.put("musica", () -> new
        // SonidoMusica("ruta/al/musica_de_fondo.wav"));
    }

    // Método para crear un sonido a partir de su tipo
    public static Sonido crearSonido(String tipo) {
        CreadorSonido creador = registroSonidos.get(tipo);
        if (creador == null) {
            throw new IllegalArgumentException("Tipo de sonido no soportado: " + tipo);
        }
        return creador.crearSonido();
    }

    // Método para registrar nuevos tipos de sonido
    public static void registrarSonido(String tipo, CreadorSonido creadorSonido) {
        registroSonidos.put(tipo, creadorSonido);
    }
}
