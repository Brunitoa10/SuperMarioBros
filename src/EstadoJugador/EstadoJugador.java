package EstadoJugador;

import Logica.Nivel;

public interface EstadoJugador {
    public void recibeDanio(Nivel nivel);
    public boolean puedeLanzarBolaFuego();
    public void actualizarSprite();
    public boolean puedeRomperBloques();

}