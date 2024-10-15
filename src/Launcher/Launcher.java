package Launcher;

import java.awt.EventQueue;

import Logica.Juego;
import Vista.GUI;

public class Launcher {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Juego juego = new Juego();
                    GUI controlador_vistas = new GUI(juego);
                    juego.set_controlador_vistas(controlador_vistas);
                    controlador_vistas.mostrar_pantalla_modo_juego();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
