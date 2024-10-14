package Logica;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OyenteTeclado implements KeyListener {

    private static OyenteTeclado instancia;


    protected boolean teclaArriba,teclaDerecha,teclaIzquierda;

    public OyenteTeclado() {
        // ... (código existente) ...
    }

    public static OyenteTeclado getInstancia() {
        if (instancia == null) {
            instancia = new OyenteTeclado();
        }
        return instancia;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            teclaArriba = true;
        }
        if (code == KeyEvent.VK_A) {
            teclaIzquierda = true;
        }
        if (code == KeyEvent.VK_D) {
            teclaDerecha = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            teclaArriba = false;
        }
        if (code == KeyEvent.VK_A) {
            teclaIzquierda = false;
        }
        if (code == KeyEvent.VK_D) {
            teclaDerecha = false;
        }

    }
}