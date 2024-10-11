package Vista.Controladores;

import Entidades.Jugador;
import java.awt.event.KeyEvent;

public class ControlTeclado {
    protected Jugador mario;

    public ControlTeclado(Jugador mario) {
        this.mario = mario;
    }

    public void teclaApretada(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_A:
            {
                mario.desplazarEnX(-1);
                break;
            }
            case KeyEvent.VK_D:
            {
                mario.desplazarEnX(1);
                break;
            }
            case KeyEvent.VK_W:
            {
                mario.saltar();
            }
        }
    }

    public void teclaSoltada(KeyEvent e) {
        int keyCode = e.getKeyCode();
    }
}
