package Generador.GestorSonido;

import java.util.HashMap;
import java.util.Map;

public class SonidoFactory {
    protected static final Map<String, CreadorSonido> registroSonidos = new HashMap<>();

    static {
        registroSonidos.put("alternativo_salto", () -> new SonidoGenerico("src/Recursos/Sonidos/alternativo/Jump.wav"));
        // registroSonidos.put("alternativo_musicaNivel", () -> new
        // SonidoGenerico("src/Recursos/Sonidos/alternativo/Song.wav"));
        registroSonidos.put("alternativo_boton",
                () -> new SonidoGenerico("src/Recursos/Sonidos/alternativo/boton.wav"));
        // registroSonidos.put("alternativo_perdiste",() -> new
        // SonidoGenerico("src/Recursos/Sonidos/alternativo/gameOver.wav"));
        // registroSonidos.put("alternativo_ranking",() -> new
        // SonidoGenerico("src/Recursos/Sonidos/alternativo/Song.wav"));

        registroSonidos.put("original_salto", () -> new SonidoGenerico("src/Recursos/Sonidos/original/Jump.wav"));
        // registroSonidos.put("original_musicaNivel", () -> new
        // SonidoGenerico("src/Recursos/Sonidos/original/Song.wav"));
        registroSonidos.put("original_boton", () -> new SonidoGenerico("src/Recursos/Sonidos/original/boton.wav"));
        // registroSonidos.put("original_perdiste",() -> new
        // SonidoGenerico("src/Recursos/Sonidos/original/gameOver.wav"));
        // registroSonidos.put("original_ranking", () -> new
        // SonidoGenerico("src/Recursos/Sonidos/original/Song.wav"));
    }

    public static Sonido crearSonido(String modoJuego, String tipoSonido) {
        String clave = modoJuego + "_" + tipoSonido; // Construye la clave usando modo y tipo
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
