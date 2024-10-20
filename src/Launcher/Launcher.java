package Launcher;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import Vista.GUI;

public class Launcher {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GUI controladorVistas = new GUI();
                    controladorVistas.mostrarPantallaModoJuego();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}