package Entidades;

import Fabricas.Sprite;
import Generador.GestorSonido.Sonido;
import Generador.GestorSonido.SonidoFactory;
import Logica.ConfiguracionJuego;

public abstract class EntidadMovil extends Entidad {

    protected int direccion;
    protected int velocidad;
    protected boolean estaVivo;
    

    public EntidadMovil(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        direccion = 0;
        estaVivo = true;
    }
    
    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public boolean estaVivo() {
        return estaVivo;
    }
    
    //Sonidos
    public void saltar() {
    	sonido = SonidoFactory.crearSonido(ConfiguracionJuego.obtenerInstancia().getModoJuego(), "salto");
    	sonido.reproducir();
    }
}
