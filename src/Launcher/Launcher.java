package Launcher;

import Logica.Juego;
import Vista.GUI;
import java.awt.EventQueue;

public class Launcher {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Juego juego = new Juego();
                    GUI controlador_vistas = new GUI(juego);
                    juego.setControladorVistas(controlador_vistas);
                    controlador_vistas.mostrarPantallaModoJuego();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
