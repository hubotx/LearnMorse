package pl.hubot.dev.learn_morse.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Class that represents basics settings of keyer.
 */
public class Settings {
	private static final Settings INSTANCE = new Settings();
	private static final Properties PROPERTIES = new Properties();

	private int pauseBeforeKeying;
	private int keyingSpeed;
	private int dotLength;
	private int dashLength;
	private int lengthOfSpaceBetweenCharacters;
	private int lengthOfSpaceBetweenWords;
	private int lengthOfSpaceBetweenCharElements;
	private int frequency;
	private float volume;
	private String pool;

	/**
	 * Load properties
	 * @throws IOException if loading properties will fails
	 */
	public static Settings getInstance() throws IOException, NoSuchFieldException, IllegalAccessException {
		Class<?> aClass = INSTANCE.getClass();
		ClassLoader classLoader = aClass.getClassLoader();
		String filename = "app.properties";
		try (InputStream input = classLoader.getResourceAsStream(filename)) {
			if (input == null) {
				throw new FileNotFoundException("Sorry, unable to find " + filename);
			}

			// load a properties file from class path, inside public static  method
			PROPERTIES.load(input);

			for (String key : PROPERTIES.stringPropertyNames()) {
				String value = PROPERTIES.getProperty(key);
				Field field = aClass.getDeclaredField(key);
				Class<?> fieldType = field.getType();
				if (fieldType.equals(Integer.TYPE)) {
					field.setInt(INSTANCE, Integer.parseInt(value));
				} else if (fieldType.equals(Float.TYPE)) {
					field.setFloat(INSTANCE, Float.parseFloat(value));
				} else {
					field.set(INSTANCE, value);
				}
			}

			return INSTANCE;
		}
	}

	private Settings() {
	}

	public void apply() throws IllegalAccessException {
		Class<?> aClass = INSTANCE.getClass();
		for (Field field : aClass.getDeclaredFields()) {
			if (!field.getName().equals("INSTANCE") && !field.getName().equals("PROPERTIES")) {
				PROPERTIES.setProperty(field.getName(), String.valueOf(field.get(INSTANCE)));
			}
		}
	}

	public int getPauseBeforeKeying() {
		return pauseBeforeKeying;
	}

	public void setPauseBeforeKeying(int pauseBeforeKeying) {
		this.pauseBeforeKeying = pauseBeforeKeying;
	}

	public int getKeyingSpeed() {
		return keyingSpeed;
	}

	public void setKeyingSpeed(int keyingSpeed) {
		this.keyingSpeed = keyingSpeed;
	}

	public int getDotLength() {
		return dotLength;
	}

	public void setDotLength(int dotLength) {
		this.dotLength = dotLength;
	}

	public int getDashLength() {
		return dashLength;
	}

	public void setDashLength(int dashLength) {
		this.dashLength = dashLength;
	}

	public int getLengthOfSpaceBetweenCharacters() {
		return lengthOfSpaceBetweenCharacters;
	}

	public void setLengthOfSpaceBetweenCharacters(int lengthOfSpaceBetweenCharacters) {
		this.lengthOfSpaceBetweenCharacters = lengthOfSpaceBetweenCharacters;
	}

	public int getLengthOfSpaceBetweenWords() {
		return lengthOfSpaceBetweenWords;
	}

	public void setLengthOfSpaceBetweenWords(int lengthOfSpaceBetweenWords) {
		this.lengthOfSpaceBetweenWords = lengthOfSpaceBetweenWords;
	}

	public int getLengthOfSpaceBetweenCharElements() {
		return lengthOfSpaceBetweenCharElements;
	}

	public void setLengthOfSpaceBetweenCharElements(int lengthOfSpaceBetweenCharElements) {
		this.lengthOfSpaceBetweenCharElements = lengthOfSpaceBetweenCharElements;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public String getPool() {
		return pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}
}
