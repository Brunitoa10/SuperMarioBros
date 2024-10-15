package Logica;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OyenteTeclado implements KeyListener {


    protected boolean teclaArriba,teclaDerecha,teclaIzquierda;

    public OyenteTeclado() {

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