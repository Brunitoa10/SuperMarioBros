package EstadoMovimiento;

public interface EstadoMovimiento {
    public void saltar();

    public void desplazarEnX(int direccion);

    public void actualizar();

    public boolean estaEnElSuelo();
}
