package Entidades;

import Fabricas.Sprite;
import Generador.GestorSonido.Sonido;
import Generador.GestorSonido.SonidoFactory;
import Logica.ConfiguracionJuego;

import java.awt.*;

public abstract class EntidadMovil extends Entidad {

    protected int direccion;
    protected int velocidad;
    protected boolean estaVivo;
    protected Sonido sonido;
    protected int Piso;

    public EntidadMovil(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        direccion = 0;
        estaVivo = true;
        Piso=420;
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

    public void setPiso(int piso) {
        Piso=piso;
    }

    public int getPiso() {
        return Piso;
    }

    @Override
    public boolean detectarColision(Entidad entidad) {
        return this.getHitbox().intersects(entidad.getHitbox());
    }

    private int calcularSolapamientoX(Rectangle hitbox1, Rectangle hitbox2) {
        int solapamientoX = ((int) (Math.min(hitbox1.getMaxX(), hitbox2.getMaxX()) - Math.max(hitbox1.getMinX(), hitbox2.getMinX())));
        return Math.max(solapamientoX, 0); // Asegurar que no sea negativo
    }

    private int calcularSolapamientoY(Rectangle hitbox1, Rectangle hitbox2) {
        int solapamientoY = ((int) (Math.min(hitbox1.getMaxY(), hitbox2.getMaxY()) - Math.max(hitbox1.getMinY(), hitbox2.getMinY())));
        return Math.max(solapamientoY, 0); // Asegurar que no sea negativo
    }

    public boolean colisionArriba(Entidad entidad) {
        int solapamientoY = calcularSolapamientoY(this.getHitbox(), entidad.getHitbox());
        int solapamientoX = calcularSolapamientoX(this.getHitbox(), entidad.getHitbox());
        int rangoColision = (int)(entidad.getHitbox().getMaxY() - entidad.getHitbox().getHeight()/2);
        boolean colisiona = (this.getHitbox().getMinY() <= entidad.getHitbox().getMaxY()) && (this.getHitbox().getMinY() >= rangoColision);
        return colisiona && (solapamientoX >= solapamientoY) || posicionY== entidad.getPosicionEnY();
    }

    public boolean colisionAbajo(Entidad entidad) {
        int solapamientoY = calcularSolapamientoY(this.getHitbox(), entidad.getHitbox());
        int solapamientoX = calcularSolapamientoX(this.getHitbox(), entidad.getHitbox());
        int rangoColision = (int)(entidad.getHitbox().getMinY() + entidad.getHitbox().getHeight()/2);
        boolean colisiona = (this.getHitbox().getMaxY() >= entidad.getHitbox().getMinY()) && (this.getHitbox().getMaxY() <= rangoColision);
        return colisiona && (solapamientoX >= solapamientoY);
    }

    public boolean colisionIzquierda(Entidad entidad) {
        int solapamientoY = calcularSolapamientoY(this.getHitbox(), entidad.getHitbox());
        int solapamientoX = calcularSolapamientoX(this.getHitbox(), entidad.getHitbox());
        int rangoColision = (int)(entidad.getHitbox().getMaxX() - entidad.getHitbox().getWidth()/2);
        boolean colisiona = (this.getHitbox().getMinX() <= entidad.getHitbox().getMaxX()) && (this.getHitbox().getMinX() >= rangoColision);
        return colisiona && (solapamientoY > solapamientoX);
    }

    public boolean colisionDerecha(Entidad entidad) {
        int solapamientoY = calcularSolapamientoY(this.getHitbox(), entidad.getHitbox());
        int solapamientoX = calcularSolapamientoX(this.getHitbox(), entidad.getHitbox());
        int rangoColision = (int) (entidad.getHitbox().getMinX() + entidad.getHitbox().getWidth()/2);
        boolean colisiona = (this.getHitbox().getMaxX() >= entidad.getHitbox().getMinX()) && (this.getHitbox().getMaxX() <= rangoColision);
        return colisiona && (solapamientoY > solapamientoX);
    
    //Sonidos
    public void saltar() {
    	sonido = SonidoFactory.crearSonido(ConfiguracionJuego.obtenerInstancia().getModoJuego(), "salto");
    	sonido.reproducir();
    }
}
