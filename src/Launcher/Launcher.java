package Launcher;

import java.awt.EventQueue;

import Vista.GUI;

public class Launcher {
    
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                	new GUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}