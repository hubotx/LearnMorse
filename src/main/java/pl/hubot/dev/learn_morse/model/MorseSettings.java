package pl.hubot.dev.learn_morse.model;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that represents basics settings of keyer.
 */
public class MorseSettings {
	private static final Properties properties = new Properties();

	/**
	 * Load properties
	 * @throws IOException if loading properties will fails
	 */
	public static void load() throws IOException {
		String filename = "app.properties";
		try (InputStream input = MorseSettings.class.getClassLoader().getResourceAsStream(filename)) {
			if (input == null) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Sorry, unable to find " + filename);
				alert.showAndWait();
				return;
			}

			// load a properties file from class path, inside public static  method
			properties.load(input);
		}
	}

	/**
	 * Get properties.
	 * @return properties
	 */
	public static Properties getProperties() {
		return properties;
	}
}
