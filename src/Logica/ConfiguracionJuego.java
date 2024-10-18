package Logica;

public class ConfiguracionJuego {
    private static ConfiguracionJuego instancia;
    private String modoJuego;

    private ConfiguracionJuego() {
    	
    }

    public static ConfiguracionJuego obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionJuego();
        }
        return instancia;
    }

    public String getModoJuego() {
        return modoJuego;
    }

    public void setModoJuego(String modoJuego) {
        this.modoJuego = modoJuego;
    }
}

