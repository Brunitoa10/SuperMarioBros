package EstadoJugador;

import Logica.Nivel;

public interface EstadoJugador {
    public boolean recibeDanio();
    public boolean puedeLanzarBolaFuego();
    public void actualizarSprite();
    public boolean puedeRomperBloques();
    public boolean elHongoLoHaceSuperMario();
    public boolean puedeSerMarioFuego();

}