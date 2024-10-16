package Generador.GestorSonido;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SonidoSalto implements Sonido {

    protected Clip clip;

    public SonidoSalto(String rutaArchivo) {
        try {
            // Cargar el archivo de sonido
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(rutaArchivo));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reproducir() {
        if (clip != null) {
            clip.setFramePosition(0); // Reiniciar el sonido desde el principio
            clip.start(); // Comienza a reproducir el sonido
        }
    }

    @Override
    public void pausar() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Pausa el sonido
        }
    }

    @Override
    public void detener() {
        if (clip != null) {
            clip.stop(); // Detiene el sonido
            clip.setFramePosition(0); // Reinicia el clip para comenzar desde el principio
        }
    }
}
