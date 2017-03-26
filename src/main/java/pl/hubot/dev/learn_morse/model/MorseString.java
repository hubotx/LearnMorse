package pl.hubot.dev.learn_morse.model;

import javax.sound.sampled.*;

/**
 * Class purposed to provide basics functionality to receive and transmit Morse code.
 */
public class MorseString {
//	/**
//	 * Receive Morse code.
//	 * @param input input string
//	 */
//	public static void receive(String input) {
//
//	}

	/**
	 * Transmit Morse code.
	 * @param input input string
	 * @throws LineUnavailableException sound(...) method
	 * @throws InterruptedException Thread.sleep(...) method
	 */
	public static void transmit(String input)
			throws LineUnavailableException, InterruptedException {
		Keyer.pauseBeforeKeying();
		String lowerCaseInput = input.toLowerCase();
		for (int i = 0; i < lowerCaseInput.length(); i++) {
			char current = lowerCaseInput.charAt(i);
			String encodedCharacter = MorseCode.morseCode.getOrDefault(Character.toString(current), "");
			for (int j = 0; j < encodedCharacter.length(); j++) {
				char currentEnc = encodedCharacter.charAt(j);
				if (currentEnc == '_') Keyer.dash();
				else if (currentEnc == '.') Keyer.dot();
			}
			Keyer.pauseChar();

			if (current == ' ') Keyer.pauseWord();
		}
	}
}
