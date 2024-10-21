package Generador.GestorSonido;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SonidoGenerico implements Sonido {
    private Clip clip;

    public SonidoGenerico(String rutaArchivo) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(rutaArchivo));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reproducir() {
        if (clip != null) {
            clip.setFramePosition(0); // Reinicia el clip al inicio
            clip.start(); // Reproduce el clip
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
