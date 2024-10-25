package EstadoJugador;

import Logica.Nivel;

public interface EstadoJugador {
    public void recibeDanio();
    public boolean puedeLanzarBolaFuego();
    public void actualizarSprite();
    public boolean puedeRomperBloques();
    public boolean elHongoLoHaceSuperMario();
    public boolean puedeSerMarioFuego();
    public boolean esInmortal();
    public String finalAnimacion();
    public boolean estadoEstrella();
}