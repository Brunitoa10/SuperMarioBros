package EstadoJugador;

import Entidades.Entidad;

public interface EstadoJugador {
    public void recibeDanio(Entidad e);

    public boolean puedeLanzarBolaFuego();

    public boolean puedeRomperBloques();

    public boolean elHongoLoHaceSuperMario();

    public boolean puedeSerMarioFuego();

    public String finalAnimacion();

    public int getPuntaje(int columna);

    public String inicioAnimacion();

    public void actualizarEstadoJugador();
}