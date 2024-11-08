package Generador.GestorSonido;

import java.util.HashMap;
import java.util.Map;

public class SonidoFactory {
    protected static final Map<String, CreadorSonido> registroSonidos = new HashMap<>();

    static {
        registroSonidos.put("alternativo_salto", () -> new SonidoGenerico("src/Recursos/Sonidos/alternativo/Jump.wav"));
        registroSonidos.put("alternativo_boton", () -> new SonidoGenerico("src/Recursos/Sonidos/alternativo/boton.wav"));
        registroSonidos.put("alternativo_nivel",() -> new SonidoGenerico("src/Recursos/Sonidos/alternativo/Song.wav"));
        registroSonidos.put("alternativo_muerte", () -> new SonidoGenerico("src/Recursos/Sonidos/alternativo/muerte.wav"));
        registroSonidos.put("alternativo_bolaFuego", () -> new SonidoGenerico("src/Recursos/Sonidos/alternativo/bolaFuego.wav"));
        registroSonidos.put("alternativo_moneda", () -> new SonidoGenerico("src/Recursos/Sonidos/alternativo/moneda.wav"));
        registroSonidos.put("alternativo_marioPowerUp", () -> new SonidoGenerico("src/Recursos/Sonidos/alternativo/marioPowerUp.wav"));

        registroSonidos.put("original_salto", () -> new SonidoGenerico("src/Recursos/Sonidos/original/Jump.wav"));
        registroSonidos.put("original_nivel", () -> new SonidoGenerico("src/Recursos/Sonidos/original/Song.wav"));
        registroSonidos.put("original_boton", () -> new SonidoGenerico("src/Recursos/Sonidos/original/boton.wav"));
        registroSonidos.put("original_muerte", () -> new SonidoGenerico("src/Recursos/Sonidos/original/muerte.wav"));
        registroSonidos.put("original_bolaFuego", () -> new SonidoGenerico("src/Recursos/Sonidos/original/bolaFuego.wav"));
        registroSonidos.put("original_moneda", () -> new SonidoGenerico("src/Recursos/Sonidos/original/moneda.wav"));
        registroSonidos.put("original_marioPowerUp", () -> new SonidoGenerico("src/Recursos/Sonidos/original/marioPowerUp.wav"));

    }

    public static Sonido crearSonido(String modoJuego, String tipoSonido) {
        String clave = modoJuego + "_" + tipoSonido;
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
