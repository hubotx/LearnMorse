package pl.hubot.dev.learn_morse.model;

import pl.hubot.dev.learn_morse.util.Settings;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.io.IOException;

public class Keyer {
    private Settings settings = Settings.getInstance();

    public Keyer()
            throws IllegalAccessException,
            NoSuchFieldException,
            IOException {
    }

    final void dash() throws LineUnavailableException, InterruptedException {
        tone(settings.getDashLength());
        pauseCharElement();
    }

    final void dot() throws LineUnavailableException, InterruptedException {
        tone(settings.getDotLength());
        pauseCharElement();
    }

    final void pauseBeforeKeying() throws InterruptedException {
        Thread.sleep(settings.getPauseBeforeKeying());
    }

    private void pauseCharElement() throws InterruptedException {
        Thread.sleep(settings.getLengthOfSpaceBetweenCharElements());
    }

    final void pauseChar() throws InterruptedException {
        Thread.sleep(settings.getLengthOfSpaceBetweenCharacters());
    }

    final void pauseWord() throws InterruptedException {
        Thread.sleep(settings.getLengthOfSpaceBetweenWords());
    }

    private void tone(final int duration) throws LineUnavailableException {
        byte[] buffer = new byte[duration * Byte.SIZE];
        final int samplingFrequencyInHz = 8000;
        for (int i = 0; i < buffer.length; i++) {
            double angle = 2 * Math.PI
                    * i * settings.getFrequency()
                    / samplingFrequencyInHz;
            buffer[i] = (byte)
                    (Math.sin(angle)
                            * settings.getVolume()
                            * Byte.MAX_VALUE);
        }
        final int numberOfFadeSamples = 80;
        for (int i = 0; i < numberOfFadeSamples
                && i < buffer.length / 2; i++) {
            buffer[i] = (byte) (buffer[i] * i
                    / numberOfFadeSamples);
            buffer[buffer.length - 1 - i] = (byte)
                    (buffer[buffer.length - 1 - i]
                            * i
                            / numberOfFadeSamples);
        }
        AudioFormat audioFormat = new AudioFormat(
                samplingFrequencyInHz, Byte.SIZE,
                1, true, false);
        SourceDataLine sourceDataLine =
                AudioSystem.getSourceDataLine(audioFormat);
        sourceDataLine.open(audioFormat);
        sourceDataLine.start();
        sourceDataLine.write(buffer, 0, buffer.length);
        sourceDataLine.drain();
        sourceDataLine.close();
    }
}
