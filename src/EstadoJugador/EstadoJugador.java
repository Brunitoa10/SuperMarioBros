package EstadoJugador;

import Logica.Nivel;

public interface EstadoJugador {
    public void recibeDanio(Nivel nivel);
    public void lanzarBolaFuego();
    public boolean puedeRomperBloques();
}