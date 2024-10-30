package Launcher;

import Vista.GUI;

import java.awt.*;

public class Launcher {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.out.println("ANDA BIEN BRUNO");
                try {
                    new GUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}