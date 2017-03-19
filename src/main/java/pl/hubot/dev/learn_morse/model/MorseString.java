package pl.hubot.dev.learn_morse.model;

import javax.sound.sampled.*;

/**
 * Class purposed to provide basics functionality to receive and transmit Morse code.
 */
public class MorseString {
	/**
	 * Receive Morse code and save result.
	 * @param input input string
	 */
	public static void receive(String input) {
		decoded = "";
		String lowerCaseInput = input.toLowerCase();
		for (String current : lowerCaseInput.split(" ")) {
			switch (current) {
				case "._": // a
					decoded += "a";
					break;
				case "_...": // b
					decoded += "b";
					break;
				case "_._.": // c
					decoded += "c";
					break;
				case "_..": // d
					decoded += "d";
					break;
				case ".": // e
					decoded += "e";
					break;
				case ".._.": // f
					decoded += "f";
					break;
				case "__.": // g
					decoded += "g";
					break;
				case "....": // h
					decoded += "h";
					break;
				case "..": // i
					decoded += "i";
					break;
				case ".___": // j
					decoded += "j";
					break;
				case "_._": // k
					decoded += "k";
					break;
				case "._..": // l
					decoded += "l";
					break;
				case "__": // m
					decoded += "m";
					break;
				case "_.": // n
					decoded += "n";
					break;
				case "___": // o
					decoded += "o";
					break;
				case ".__.": // p
					decoded += "p";
					break;
				case "__._": // q
					decoded += "q";
					break;
				case "._.": // r
					decoded += "r";
					break;
				case "...": // s
					decoded += "s";
					break;
				case "_": // t
					decoded += "t";
					break;
				case ".._": // u
					decoded += "u";
					break;
				case "..._": // v
					decoded += "v";
					break;
				case ".__": // w
					decoded += "w";
					break;
				case "_.._": // x
					decoded += "x";
					break;
				case "_.__": // y
					decoded += "y";
					break;
				case "__..": // z
					decoded += "z";
					break;
			}

			decoded += " ";
		}
	}

	/**
	 * Get result of receiving Morse code.
	 * @return result of receiving Morse code
	 */
	public static String getDecoded() {
		return decoded;
	}

	/**
	 * Transmit Morse code and save result.
	 * @param input input string
	 * @throws LineUnavailableException sound(...) method
	 * @throws InterruptedException Thread.sleep(...) method
	 */
	public static void transmit(String input)
			throws LineUnavailableException, InterruptedException {
		encoded = "";
		pauseBeforeKeying();
		String lowerCaseInput = input.toLowerCase();
		for (int i = 0; i < lowerCaseInput.length(); i++) {
			char current = lowerCaseInput.charAt(i);
			switch (current) {
				case 'a': // ._
					dit();
					dah();
					encoded += "._";
					break;
				case 'b': // _...
					dah();
					dit();
					dit();
					dit();
					encoded += " _...";
					break;
				case 'c': // _._.
					dah();
					dit();
					dah();
					dit();
					encoded += "_._.";
					break;
				case 'd': // _..
					dah();
					dit();
					dit();
					encoded += "_..";
					break;
				case 'e': // .
					dit();
					encoded += ".";
					break;
				case 'f': // .._.
					dit();
					dit();
					dah();
					dit();
					encoded += ".._.";
					break;
				case 'g': // __.
					dah();
					dah();
					dit();
					encoded += "__.";
					break;
				case 'h': // ....
					dit();
					dit();
					dit();
					dit();
					encoded += "....";
					break;
				case 'i': // ..
					dit();
					dit();
					encoded += "..";
					break;
				case 'j': // .___
					dit();
					dah();
					dah();
					dah();
					encoded += ".___";
					break;
				case 'k': // _._
					dah();
					dit();
					dah();
					encoded += "_._";
					break;
				case 'l': // ._..
					dit();
					dah();
					dit();
					dit();
					encoded += "._..";
					break;
				case 'm': // __
					dah();
					dah();
					encoded += "__";
					break;
				case 'n': // _.
					dah();
					dit();
					encoded += "_.";
					break;
				case 'o': // ___
					dah();
					dah();
					dah();
					encoded += "___";
					break;
				case 'p': // .__.
					dit();
					dah();
					dah();
					dit();
					encoded += ".__.";
					break;
				case 'q': // __._
					dah();
					dah();
					dit();
					dah();
					encoded += "__._";
					break;
				case 'r': // ._.
					dit();
					dah();
					dit();
					encoded += "._.";
					break;
				case 's': // ...
					dit();
					dit();
					dit();
					encoded += "...";
					break;
				case 't': // _
					dah();
					encoded += "_";
					break;
				case 'u': // .._
					dit();
					dit();
					dah();
					encoded += ".._";
					break;
				case 'v': // ..._
					dit();
					dit();
					dit();
					dah();
					encoded += "..._";
					break;
				case 'w': // .__
					dit();
					dah();
					dah();
					encoded += ".__";
					break;
				case 'x': // _.._
					dah();
					dit();
					dit();
					dah();
					encoded += "_.._";
					break;
				case 'y': // _.__
					dah();
					dit();
					dah();
					dah();
					encoded += "_.__";
					break;
				case 'z': // __..
					dah();
					dah();
					dit();
					dit();
					encoded += "__..";
					break;
			}

			encoded += " ";

			if (current == ' ') {
				pauseWord();
				encoded += "       ";
			}
			else {
				pauseChar();
				encoded += "   ";
			}
		}
	}

	/**
	 * Get result of transmitted Morse code.
	 * @return result of transmitted Morse code
	 */
	public static String getEncoded() {
		return encoded;
	}

	/**
	 * Transmit dah.
	 * @throws LineUnavailableException sound(...) method
	 * @throws InterruptedException Thread.sleep(...) method
	 */
	private static void dah() throws LineUnavailableException, InterruptedException {
		sound(settings.getDahLength());
		pauseCharElement();
	}

	/**
	 * Transmit dit.
	 * @throws LineUnavailableException sound(...) method
	 * @throws InterruptedException Thread.sleep(...) method
	 */
	private static void dit() throws LineUnavailableException, InterruptedException {
		sound(settings.getDitLength());
		pauseCharElement();
	}

	/**
	 * Make pause before keying.
	 * @throws InterruptedException Thread.sleep(...) method
	 */
	private static void pauseBeforeKeying() throws InterruptedException {
		Thread.sleep(settings.getPauseBeforeKeying());
	}

	/**
	 * Make pause between two char elements.
	 * @throws InterruptedException Thread.sleep(...) method
	 */
	private static void pauseCharElement() throws InterruptedException {
		Thread.sleep(settings.getLengthOfSpaceBetweenCharElements());
	}

	/**
	 * Make pause between two chars.
	 * @throws InterruptedException Thread.sleep(...) method
	 */
	private static void pauseChar() throws InterruptedException {
		Thread.sleep(settings.getLengthOfSpaceBetweenCharacters());
	}

	/**
	 * Make pause between two words.
	 * @throws InterruptedException Thread.sleep(...) method
	 */
	private static void pauseWord() throws InterruptedException {
		Thread.sleep(settings.getLengthOfSpaceBetweenWords());
	}

	/**
	 * Play sound with defined frequency by MorseSettings object.
	 * @param msecs duration
	 * @throws LineUnavailableException thrown if writing sound buffer to audio source data line fails.
	 */
	private static void sound(int msecs) throws LineUnavailableException {
		byte[] buf = new byte[msecs * Byte.SIZE];
		for (int i = 0; i < buf.length; i++) {
			double angle = 2 * Math.PI * i * settings.getFrequency() / SAMPLING_FREQUENCY_IN_HZ;
			buf[i] = (byte) (Math.sin(angle) * settings.getVolume() * Byte.MAX_VALUE);
		}
		for (int i = 0; i < NUMBER_OF_FADE_SAMPLES && i < buf.length / 2; i++) {
			buf[i] = (byte) (buf[i] * i / NUMBER_OF_FADE_SAMPLES);
			buf[buf.length - 1 - i] = (byte) (buf[buf.length - 1 - i] * i / NUMBER_OF_FADE_SAMPLES);
		}
		AudioFormat af = new AudioFormat(SAMPLING_FREQUENCY_IN_HZ, Byte.SIZE, 1, true, false);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open(af);
		sdl.start();
		sdl.write(buf, 0, buf.length);
		sdl.drain();
		sdl.close();
	}

	/**
	 * Get MorseSettings object.
	 * @return MorseSettings object
	 */
	public static MorseSettings getSettings() {
		return settings;
	}

	/**
	 * Set MorseSettings object
	 * @param settings MorseSettings object
	 */
	public static void setSettings(MorseSettings settings) {
		MorseString.settings = settings;
	}

	private static MorseSettings settings;
	private static final int SAMPLING_FREQUENCY_IN_HZ = 8000;
	private static final int NUMBER_OF_FADE_SAMPLES = 80;
	private static String encoded;
	private static String decoded;
}
