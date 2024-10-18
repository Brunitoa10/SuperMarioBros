package Generador.GestorSonido;

import java.util.HashMap;
import java.util.Map;

public class SonidoFactory {
    protected static final Map<String, CreadorSonido> registroSonidos = new HashMap<>();

    static {
        // Registro de sonidos para el modo original
        registroSonidos.put("original_salto", () -> new SonidoGenerico("src/Recursos/Sonidos/Original/Jump.wav"));
        registroSonidos.put("original_musicaNivel", () -> new SonidoGenerico("src/Recursos/Sonidos/Original/Song.wav"));

        // Registro de sonidos para el modo alternativo
        registroSonidos.put("alternativo_salto", () -> new SonidoGenerico("src/Recursos/Sonidos/Alternativo/Jump.wav"));
        registroSonidos.put("alternativo_musicaNivel", () -> new SonidoGenerico("src/Recursos/Sonidos/Alternativo/Song.wav"));
    }

    public static Sonido crearSonido(String modoJuego, String tipoSonido) {
        String clave = modoJuego + "_" + tipoSonido;  // Construye la clave usando modo y tipo
        CreadorSonido creador = registroSonidos.get(clave);
        if (creador == null) {
            throw new IllegalArgumentException("Sonido no soportado para este modo: " + clave);
        }
        return creador.crearSonido();
    }

    public static void registrarSonido(String modoJuego, String tipoSonido, CreadorSonido creadorSonido) {
        String clave = modoJuego + "_" + tipoSonido;
        registroSonidos.put(clave, creadorSonido);
    }
}
