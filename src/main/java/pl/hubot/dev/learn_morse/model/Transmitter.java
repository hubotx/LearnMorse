package pl.hubot.dev.learn_morse.model;

import pl.hubot.dev.learn_morse.util.Settings;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * Class purposed to provide basics functionality to transmit an Morse code.
 */
public class Transmitter {
    /**
     * Settings.
     */
    private Settings settings = Settings.getInstance();

    /**
     * Last transmitted string.
     */
    private String transmitted;

    /**
     * Constructor.
     * @throws IllegalAccessException IllegalAccessException
     * @throws NoSuchFieldException NoSuchFieldException
     * @throws IOException IOException
     */
    public Transmitter()
            throws IllegalAccessException,
            NoSuchFieldException,
            IOException {
    }

    /**
     * Transmit Morse code.
     *
     * @param input input string
     * @throws LineUnavailableException LineUnavailableException
     * @throws InterruptedException     InterruptedException
     * @throws IOException              IOException
     * @throws IllegalAccessException   IllegalAccessExcepti/on
     * @throws NoSuchFieldException     NoSuchFieldException
     */
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
     * Train Morse code using blocks method.
     * @throws LineUnavailableException LineUnavailableException
     * @throws InterruptedException InterruptedException
     * @throws IOException IOException
     * @throws NoSuchFieldException NoSuchFieldException
     * @throws IllegalAccessException IllegalAccessException
     */
    public final void blocksMethod()
            throws LineUnavailableException,
            InterruptedException,
            IOException,
            NoSuchFieldException,
            IllegalAccessException {
        char[] pool = settings.getPool().toCharArray();
        StringBuilder randomCharacters = new StringBuilder();
        for (int wordIndex = 0; wordIndex < settings.getBlocksStringsToSend(); wordIndex++) {
            for (int charIndex = 0; charIndex < settings.getBlocksLengthOfStrings(); charIndex++) {
                char curr = pool[new Random().nextInt(pool.length)];
                randomCharacters.append(curr);
            }
            randomCharacters.append(' ');
        }
        transmit(randomCharacters.toString());
    }

    /**
     * Get last transmitted string.
     * @return last transmitted string
     */
    public final String getTransmitted() {
        return transmitted;
    }
}
