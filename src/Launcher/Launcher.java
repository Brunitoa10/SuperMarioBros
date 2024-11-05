package Launcher;

import Vista.GUI;

import java.awt.*;

public class Launcher {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new GUI();
                } catch (Exception e) {
                    System.err.println("No se pudo iniciar el juego");
                }
            }
        });
    }
}