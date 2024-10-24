package EstadoMovimiento;

import Entidades.Jugador;

public interface EstadoMovimiento {
    public void saltar();
    public void desplazarEnX(int direccion);
    public void actualizar();
    public void LanzarBola();
    public void EnAire();
    public void AFK() ;
}
