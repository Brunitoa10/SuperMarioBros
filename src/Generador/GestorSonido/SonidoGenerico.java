package Generador.GestorSonido;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SonidoGenerico implements Sonido {
    private Clip clip;

    public SonidoGenerico(String rutaArchivo) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(rutaArchivo));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            System.err.println("No se pudo crear el sonido: " + rutaArchivo);
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
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    @Override
    public void detener() {
        clip.stop();
        clip.close();
    }
}
