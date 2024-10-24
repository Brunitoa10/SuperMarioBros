package EstadoMovimiento;

import Entidades.Jugador;

public interface EstadoMovimiento {
    public void saltar();
    public void desplazarEnX(int direccion);
    public void actualizar();
    public boolean estaEnElSuelo();
    public void LanzarBola();
    public void EnAire(Jugador jugador);
    public void AFK(Jugador jugador) ;
}
