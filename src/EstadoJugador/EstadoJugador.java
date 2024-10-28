package EstadoJugador;

import Entidades.Entidad;

public interface EstadoJugador {
    public void recibeDanio(Entidad e);

    public boolean puedeLanzarBolaFuego();

    public void actualizarSprite();

    public boolean puedeRomperBloques();

    public boolean elHongoLoHaceSuperMario();

    public boolean puedeSerMarioFuego();

    public String finalAnimacion();

    public int getPuntajeEstrella();

    public int getPuntajeSuperChampinion();

    public int getPuntajeChampinionVerde();

    public int getPuntajeFlorDeFuego();
}