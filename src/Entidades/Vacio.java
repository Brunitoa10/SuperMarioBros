package Entidades;

import Entidades.EntidadInmovil.EntidadInmovil;
import Fabricas.Sprite;
import Logica.Temporizador;
import Visitor.Visitor;

import java.util.List;

public class Vacio extends EntidadInmovil {
    private final String RUTA_IMAGEN="src/Recursos/Sprites/original/Bloques/BloqueNada.png";
    protected Temporizador temporizador;
    protected String[] frames;
    private int currentFrame;        // Índice del frame actual
    private int frameRate;           // Velocidad de cambio de frames
    private long lastTime, timer;    // Para controlar el tiempo entre frames
    private boolean animacionActiva; // Controla si la animación está activa
    private boolean animacionFinalizada; // Controla si la animación ha terminado
    protected List<Vacio> listaVacioNivel;

    public Vacio(int x, int y, Sprite sprite, List<Vacio> listaVacioNivel) {
        super(x, y, sprite);
        temporizador=new Temporizador();
        currentFrame = 0;
        frameRate = 100;  // Cambia de frame cada 100 milisegundos
        timer = 0;
        lastTime = System.currentTimeMillis();
        animacionActiva = false;
        animacionFinalizada = false;
        this.listaVacioNivel = listaVacioNivel;
    }

    @Override
    public boolean detectarColision(Entidad c) {
        return false;
    }

    @Override
    public void accept(Visitor v) {

    }

    public void actualizarAnimacion() {
        super.actualizarEntidad();
        if (temporizador.hanPasadoNSegundos(1200)) {
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueNada.png");
        }

    }

    public void setAnimacionFinal(){
        temporizador.iniciar();
        this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/AnimacionLadrillo/AnimacionBloqueRompiendose.gif");
        animacionActiva = true;  // Activa la animación
    }

    public void actualizar() {
        if (animacionActiva) {
            actualizarAnimacion();  // Si la animación está activa, actualiza los frames

        }
    }
  
    @Override
    public void eliminarEntidad() {
        listaVacioNivel.remove(this);
    }
}
