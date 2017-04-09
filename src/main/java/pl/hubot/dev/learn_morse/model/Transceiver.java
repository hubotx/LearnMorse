package pl.hubot.dev.learn_morse.model;

import pl.hubot.dev.learn_morse.util.ErrorHandler;
import pl.hubot.dev.learn_morse.util.Settings;
import pl.hubot.dev.learn_morse.util.SpeechUtils;

import javax.sound.sampled.LineUnavailableException;
import javax.speech.AudioException;
import javax.speech.EngineException;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class Transceiver {
    private Settings settings = Settings.getInstance();
    private String transmitted;
    private SpeechUtils speechUtils = new SpeechUtils();

    public Transceiver()
            throws IllegalAccessException,
            NoSuchFieldException,
            IOException,
            AudioException,
            EngineException,
            PropertyVetoException {
        speechUtils.init();
    }

    @Override
    public final void finalize() {
        try {
            speechUtils.terminate();
        } catch (EngineException ex) {
            ErrorHandler.handleException(ex);
        }
    }

    public final void transmit(final String input)
            throws LineUnavailableException,
            InterruptedException,
            IOException,
            NoSuchFieldException,
            IllegalAccessException {
        transmitted = input;
        Keyer keyer = new Keyer();
        keyer.pauseBeforeKeying();
        String lowerCaseInput = input.toLowerCase();
        for (int i = 0; i < lowerCaseInput.length(); i++) {
            char current = lowerCaseInput.charAt(i);
            Map<String, String> morseCode =
                    new Encoder().getMorseCode();
            String encodedCharacter = morseCode
                    .getOrDefault(Character.toString(
                            current), "");
            for (int j = 0; j < encodedCharacter.length(); j++) {
                char currentEnc = encodedCharacter.charAt(j);
                if (currentEnc == '_') {
                    keyer.dash();
                } else if (currentEnc == '.') {
                    keyer.dot();
                }
            }
            keyer.pauseChar();

            if (current == ' ') {
                keyer.pauseWord();
            }
        }
    }

    /**
     * Receive Morse code.
     * @param input input
     * @return decoded Morse code
     * @throws IOException IOException
     * @throws NoSuchFieldException NoSuchFieldException
     * @throws AudioException AudioException
     * @throws EngineException EngineException
     * @throws PropertyVetoException PropertyVetoException
     * @throws InterruptedException InterruptedException
     */
    public final String receive(final String input)
            throws IOException,
            NoSuchFieldException,
            AudioException,
            EngineException,
            PropertyVetoException,
            InterruptedException {
        String decoded = new Encoder().decode(input);
        speechUtils.doSpeak(decoded);
        return decoded;
    }

    public final void blocksMethod()
            throws LineUnavailableException,
            InterruptedException,
            IOException,
            NoSuchFieldException,
            IllegalAccessException {
        char[] pool = settings.getPool().toCharArray();
        StringBuilder randomCharacters = new StringBuilder();
        for (int wordIndex = 0;
             wordIndex < settings.getBlocksStringsToSend();
             wordIndex++) {
            for (int charIndex = 0;
                 charIndex < settings.getBlocksLengthOfStrings();
                 charIndex++) {
                char curr = pool[new Random().nextInt(pool.length)];
                randomCharacters.append(curr);
            }
            randomCharacters.append(' ');
        }
        transmit(randomCharacters.toString());
    }

    public final String getTransmitted() {
        return transmitted;
    }
}
