package EstadoJugador;

import Entidades.Entidad;
import Entidades.Power_Ups.ChampinionVerde;
import Entidades.Power_Ups.Estrella;
import Entidades.Power_Ups.FlorDeFuego;
import Entidades.Power_Ups.SuperChampinion;

public interface EstadoJugador {
    public void recibeDanio(Entidad e);

    public boolean puedeLanzarBolaFuego();

    public boolean puedeRomperBloques();

    public String finalAnimacion();

    public int getPuntaje(int columna);

    public String inicioAnimacion();

    public void actualizarEstadoJugador();

    public void interactuar(SuperChampinion powerUp);

    public void interactuar(FlorDeFuego powerUp);

    public void interactuar(Estrella powerUp);

}