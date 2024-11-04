package EstadoJugador;

import Entidades.Entidad;
import Entidades.Power_Ups.Estrella;
import Entidades.Power_Ups.FlorDeFuego;
import Entidades.Power_Ups.SuperChampinion;
import Logica.ControladorBolasDeFuego;

public interface EstadoJugador {
    public void recibeDanio(Entidad e);

    public boolean puedeRomperBloques();

    public String finalAnimacion();

    public int getPuntaje(int columna);

    public String inicioAnimacion();

    public void actualizarEstadoJugador();

    public int interactuar(SuperChampinion powerUp, int COLUMNA);

    public int interactuar(FlorDeFuego powerUp, int COLUMNA);

    public int interactuar(Estrella powerUp, int COLUMNA);

    public void lanzarBolaDeFuego(ControladorBolasDeFuego controladorBolasDeFuego);

}