package pl.hubot.dev.learn_morse.model;

import javafx.scene.control.Alert;
import pl.hubot.dev.learn_morse.ErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that represents basics settings of keyer.
 */
public class MorseSettings {
	private static Properties prop = new Properties();
	private static InputStream input = null;

	/**
	 * Load properties
	 * @throws IOException if loading properties will fails
	 */
	public static void load() throws IOException {
		String filename = "config.properties";
		input = MorseSettings.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Sorry, unable to find " + filename);
			alert.showAndWait();
			return;
		}

		// load a properties file from class path, inside public static  method
		prop.load(input);
	}

	/**
	 * Close input stream.
	 */
	public static void unload() {
		if (input != null){
			try {
				input.close();
			} catch (IOException ex) {
				ErrorHandler.handleException(ex);
			}
		}
	}

	/**
	 * Get pause before keying.
	 * @return pause before keying
	 */
	public static int getPauseBeforeKeying() {
		return Integer.parseInt(prop.getProperty("pauseBeforeKeying"));
	}

	/**
	 * Set pause before keying.
	 * @param pauseBeforeKeying pause before keying
	 */
	public static void setPauseBeforeKeying(int pauseBeforeKeying) {
		prop.setProperty("pauseBeforeKeying", Integer.toString(pauseBeforeKeying));
	}

	/**
	 * Get length of space between two characters.
	 * @return length of space between two characters
	 */
	public static int getLengthOfSpaceBetweenCharacters() {
		return Integer.parseInt(prop.getProperty("lengthOfSpaceBetweenCharacters"));
	}

	/**
	 * Set length of space between two characters.
	 * @param lengthOfSpaceBetweenCharacters length of space between two characters
	 */
	public static void setLengthOfSpaceBetweenCharacters(int lengthOfSpaceBetweenCharacters) {
		prop.setProperty("lengthOfSpaceBetweenCharacters", Integer.toString(lengthOfSpaceBetweenCharacters));
	}

	/**
	 * Get length of space between two words.
	 * @return length of space between two words
	 */
	public static int getLengthOfSpaceBetweenWords() {
		return Integer.parseInt(prop.getProperty("lengthOfSpaceBetweenWords"));
	}

	/**
	 * Set length of space between two words.
	 * @param lengthOfSpaceBetweenWords length of space between two words
	 */
	public static void setLengthOfSpaceBetweenWords(int lengthOfSpaceBetweenWords) {
		prop.setProperty("lengthOfSpaceBetweenWords", Integer.toString(lengthOfSpaceBetweenWords));
	}

	/**
	 * Get length of space between two char elements.
	 * @return length of space between two char elements
	 */
	public static int getLengthOfSpaceBetweenCharElements() {
		return Integer.parseInt(prop.getProperty("lengthOfSpaceBetweenCharElements"));
	}

	/**
	 * Set length of space between two char elements.
	 * @param lengthOfSpaceBetweenCharElements length of space between two char elements
	 */
	public static void setLengthOfSpaceBetweenCharElements(int lengthOfSpaceBetweenCharElements) {
		prop.setProperty("lengthOfSpaceBetweenCharElements", Integer.toString(lengthOfSpaceBetweenCharElements));
	}

	/**
	 * Get keying speed.
	 * @return keying speed
	 */
	public static int getKeyingSpeed() {
		return Integer.parseInt(prop.getProperty("keyingSpeed"));
	}

	/**
	 * Set keying speed.
	 * @param keyingSpeed keying speed
	 */
	public static void setKeyingSpeed(int keyingSpeed) {
		prop.setProperty("keyingSpeed", Integer.toString(keyingSpeed));
	}

	/**
	 * Get keyer frequency.
	 * @return keyer frequency
	 */
	public static int getFrequency() {
		return Integer.parseInt(prop.getProperty("frequency"));
	}

	/**
	 * Set keyer frequency.
	 * @param frequency keyer frequency
	 */
	public static void setFrequency(int frequency) {
		prop.setProperty("frequency", Integer.toString(frequency));
	}

	/**
	 * Get dit length.
	 * @return dit length
	 */
	public static int getDitLength() {
		return Integer.parseInt(prop.getProperty("ditLength"));
	}

	/**
	 * Set dit length.
	 * @param ditLength dit length
	 */
	public static void setDitLength(int ditLength) {
		prop.setProperty("ditLength", Integer.toString(ditLength));
	}

	/**
	 * Get dah length.
	 * @return dah length
	 */
	public static int getDahLength() {
		return Integer.parseInt(prop.getProperty("dahLength"));
	}

	/**
	 * Set dah length.
	 * @param dahLength dah length
	 */
	public static void setDahLength(int dahLength) {
		prop.setProperty("dahLength", Integer.toString(dahLength));
	}

	/**
	 * Get buzzer volume.
	 * @return buzzer volume
	 */
	public static float getVolume() {
		return Float.parseFloat(prop.getProperty("volume"));
	}

	/**
	 * Set buzzer volume.
	 * @param volume buzzer volume
	 */
	public static void setVolume(float volume) {
		prop.setProperty("volume", Float.toString(volume));
	}

	/**
	 * Get character pool.
	 * @return pool
	 */
	public static char[] getPool() {
		return prop.getProperty("pool").toCharArray();
	}

	/**
	 * Set character pool.
	 * @param pool character pool
	 */
	public static void setPool(char[] pool) {
		prop.setProperty("pool", String.valueOf(pool));
	}
}
