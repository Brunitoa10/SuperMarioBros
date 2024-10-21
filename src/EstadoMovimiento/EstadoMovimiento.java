package EstadoMovimiento;

import Entidades.Jugador;

public interface EstadoMovimiento {
    public void saltar(Jugador j);
    public void desplazarEnX(int direccion);
    public void actualizar();
    public boolean estaEnElSuelo();
}
