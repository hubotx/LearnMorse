package pl.hubot.dev.learn_morse.model;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.Map;

/**
 * Class purposed to provide basics functionality to transmit Morse code.
 */
public class Transmitter {
    /**
     * Transmit Morse code.
     *
     * @param input input string
     * @throws LineUnavailableException LineUnavailableException
     * @throws InterruptedException     InterruptedException
     * @throws IOException              IOException
     * @throws IllegalAccessException   IllegalAccessException
     * @throws NoSuchFieldException     NoSuchFieldException
     */
    public final void transmit(final String input)
            throws LineUnavailableException,
            InterruptedException,
            IOException,
            NoSuchFieldException,
            IllegalAccessException {
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
}
