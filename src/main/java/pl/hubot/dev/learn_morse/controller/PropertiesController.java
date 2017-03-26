package pl.hubot.dev.learn_morse.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.hubot.dev.learn_morse.model.CharPool;
import pl.hubot.dev.learn_morse.model.MorseSettings;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for resource view/Properties.fxml.
 */
public class PropertiesController implements Initializable {
	@FXML private TextField pauseBeforeKeying;
	@FXML private TextField keyingSpeed;
	@FXML private TextField dotLength;
	@FXML private TextField dashLength;
	@FXML private TextField lengthOfSpaceBetweenCharacters;
	@FXML private TextField lengthOfSpaceBetweenWords;
	@FXML private TextField lengthOfSpaceBetweenCharElements;
	@FXML private TextField frequency;
	@FXML private TextField volume;
	@FXML private ComboBox<String> charPool;
	@FXML private Button closeButton;

	/**
	 * Initialize controller.
	 * @param location location
	 * @param resources resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pauseBeforeKeying.setText(MorseSettings.getProperties().getProperty("pauseBeforeKeying"));
		keyingSpeed.setText(MorseSettings.getProperties().getProperty("keyingSpeed"));
		dotLength.setText(MorseSettings.getProperties().getProperty("dotLength"));
		dashLength.setText(MorseSettings.getProperties().getProperty("dashLength"));
		lengthOfSpaceBetweenCharacters.setText(String.valueOf(
				Integer.parseInt(MorseSettings.getProperties().getProperty("lengthOfSpaceBetweenCharacters"))
				/ Integer.parseInt(MorseSettings.getProperties().getProperty("dotLength"))));
		lengthOfSpaceBetweenWords.setText(String.valueOf(
				Integer.parseInt(MorseSettings.getProperties().getProperty("lengthOfSpaceBetweenWords"))
						/ Integer.parseInt(MorseSettings.getProperties().getProperty("dotLength"))));
		lengthOfSpaceBetweenCharElements.setText(String.valueOf(
				Integer.parseInt(MorseSettings.getProperties().getProperty("lengthOfSpaceBetweenCharElements"))
						/ Integer.parseInt(MorseSettings.getProperties().getProperty("dotLength"))));
		frequency.setText(MorseSettings.getProperties().getProperty("frequency"));
		volume.setText(String.valueOf(Float.parseFloat(MorseSettings.getProperties().getProperty("volume")) * 100.0f));

		charPool.getItems().add(String.valueOf(CharPool.englishSet));
		charPool.getItems().add(String.valueOf(CharPool.polishSet));
		charPool.getItems().add(String.valueOf(CharPool.digits));
		charPool.getItems().add(String.valueOf(CharPool.alphanumeric));
		charPool.getItems().add(String.valueOf(CharPool.polishAlphanumeric));
		charPool.getItems().add(String.valueOf(CharPool.symbols));
		charPool.getItems().add(String.valueOf(CharPool.fullCharacterSet));

		charPool.getSelectionModel().select(String.valueOf(MorseSettings.getProperties().getProperty("pool")));
	}

	/**
	 * Close window.
	 */
	public void cancel() {
		// get a handle to the stage
		Stage stage = (Stage) closeButton.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	/**
	 * Apply changes.
	 */
	public void ok() {
		// set properties
		MorseSettings.getProperties().setProperty("pauseBeforeKeying", pauseBeforeKeying.getText());
		MorseSettings.getProperties().setProperty("lengthOfSpaceBetweenCharacters",
				String.valueOf(Integer.parseInt(lengthOfSpaceBetweenCharacters.getText())
						* Integer.parseInt(MorseSettings.getProperties().getProperty("dotLength"))));
		MorseSettings.getProperties().setProperty("lengthOfSpaceBetweenWords",
				String.valueOf(Integer.parseInt(lengthOfSpaceBetweenWords.getText())
						* Integer.parseInt(MorseSettings.getProperties().getProperty("dotLength"))));
		MorseSettings.getProperties().setProperty("lengthOfSpaceBetweenCharElements",
				String.valueOf(Integer.parseInt(lengthOfSpaceBetweenCharElements.getText())
						* Integer.parseInt(MorseSettings.getProperties().getProperty("dotLength"))));
		MorseSettings.getProperties().setProperty("keyingSpeed", keyingSpeed.getText());
		MorseSettings.getProperties().setProperty("dotLength", dotLength.getText());
		MorseSettings.getProperties().setProperty("dashLength", dashLength.getText());
		MorseSettings.getProperties().setProperty("frequency", frequency.getText());
		MorseSettings.getProperties().setProperty("volume", String.valueOf(Integer.parseInt(volume.getText()) / 100.0f));
		MorseSettings.getProperties().setProperty("charPool", charPool.getSelectionModel().getSelectedItem());
		// get a handle to the stage
		Stage stage = (Stage) closeButton.getScene().getWindow();
		// do what you have to do
		stage.close();
	}
}
