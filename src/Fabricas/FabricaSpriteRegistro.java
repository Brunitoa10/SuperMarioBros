package Fabricas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FabricaSpriteRegistro {
    private Map<String, Supplier<FabricaSprites>> registry;

    public FabricaSpriteRegistro() {
        System.out.println("FabricaSpriteRegistro");
        registry = new HashMap<>();

        // Registrar diferentes fábricas de sprites para cada modo de juego
        registry.put("original", () -> new FabricaSpritesOriginal("src/Recursos/Sprites/original"));
        registry.put("alternativo", () -> new FabricaSpritesAlternativa("src/Recursos/Sprites/alternativo"));
        // Agregar más modos de juego y sus fábricas aquí
    }

    public FabricaSprites obtenerFabrica(String modoJuego) {
        Supplier<FabricaSprites> supplier = registry.get(modoJuego.toLowerCase());

        if (supplier != null) {
            return supplier.get();
        }

        // Si no existe una fábrica específica, puedes retornar una fábrica por defecto o lanzar una excepción
        throw new IllegalArgumentException("Modo de juego no soportado: " + modoJuego);
    }

}
