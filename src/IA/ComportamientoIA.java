package IA;

import Entidades.Enemigos.Enemigo;

public interface ComportamientoIA {
    public void actualizar(Enemigo enemigo);
    public boolean comienzoAtaque();
}
