package Generador.GestorSonido;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SonidoGenerico implements Sonido {

    protected Clip clip;

    public SonidoGenerico(String rutaArchivo) {
        try {
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
            clip.setFramePosition(0);
            clip.start();
        }
    }

    @Override
    public void pausar() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    @Override
    public void detener() {
        if (clip != null) {
            clip.stop(); 
            clip.setFramePosition(0);
        }
    }
}
