package pl.hubot.dev.learn_morse.model;

import pl.hubot.dev.learn_morse.util.Settings;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.io.IOException;

/**
 * A class that is responding from buzzer sound and transmitting Morse signals.
 */
public class Keyer {
    /**
     * Application settings.
     */
    private Settings settings = Settings.getInstance();

    /**
     * Initialize Keyer.
     *
     * @throws IllegalAccessException IllegalAccessException
     * @throws NoSuchFieldException   NoSuchFieldException
     * @throws IOException            IOException
     */
    public Keyer()
            throws IllegalAccessException,
            NoSuchFieldException,
            IOException {
    }

    /**
     * Transmit dash.
     *
     * @throws LineUnavailableException sound(...) method
     * @throws InterruptedException     Thread.sleep(...) method
     */
    void dash() throws LineUnavailableException, InterruptedException {
        tone(settings.getDashLength());
        pauseCharElement();
    }

    /**
     * Transmit dot.
     *
     * @throws LineUnavailableException sound(...) method
     * @throws InterruptedException     Thread.sleep(...) method
     */
    void dot() throws LineUnavailableException, InterruptedException {
        tone(settings.getDotLength());
        pauseCharElement();
    }

    /**
     * Make pause before keying.
     *
     * @throws InterruptedException Thread.sleep(...) method
     */
    void pauseBeforeKeying() throws InterruptedException {
        Thread.sleep(settings.getPauseBeforeKeying());
    }

    /**
     * Make pause between two char elements.
     *
     * @throws InterruptedException Thread.sleep(...) method
     */
    private void pauseCharElement() throws InterruptedException {
        Thread.sleep(settings.getLengthOfSpaceBetweenCharElements());
    }

    /**
     * Make pause between two chars.
     *
     * @throws InterruptedException Thread.sleep(...) method
     */
    void pauseChar() throws InterruptedException {
        Thread.sleep(settings.getLengthOfSpaceBetweenCharacters());
    }

    /**
     * Make pause between two words.
     *
     * @throws InterruptedException Thread.sleep(...) method
     */
    void pauseWord() throws InterruptedException {
        Thread.sleep(settings.getLengthOfSpaceBetweenWords());
    }

    /**
     * Play sound with defined frequency by MorseSettings object.
     *
     * @param duration duration
     * @throws LineUnavailableException LineUnavailableException
     */
    public void tone(final int duration) throws LineUnavailableException {
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
