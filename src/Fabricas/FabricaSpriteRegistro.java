package Fabricas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FabricaSpriteRegistro {
    private Map<String, Supplier<FabricaSprites>> registry;

    public FabricaSpriteRegistro() {
        System.out.println("FabricaSpriteRegistro");
        registry = new HashMap<>();
        registry.put("original", () -> new FabricaSpritesOriginal("src/Recursos/Sprites/original"));
        registry.put("alternativo", () -> new FabricaSpritesAlternativa("src/Recursos/Sprites/alternativo"));
    }

    public FabricaSprites obtenerFabrica(String modoJuego) {
        Supplier<FabricaSprites> supplier = registry.get(modoJuego.toLowerCase());
        if (supplier != null) {
            return supplier.get();
        }
        throw new IllegalArgumentException("Modo de juego no soportado: " + modoJuego);
    }

}
