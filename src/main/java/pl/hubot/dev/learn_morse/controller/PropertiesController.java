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
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Controller class for resource view/Properties.fxml.
 */
public class PropertiesController implements Initializable {
	/**
	 * Initialize controller.
	 * @param location location
	 * @param resources resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pauseBeforeKeying.setText(Integer.toString(MorseSettings.getPauseBeforeKeying()));
		keyingSpeed.setText(Integer.toString(MorseSettings.getKeyingSpeed()));
		ditLength.setText(Integer.toString(MorseSettings.getDitLength()));
		dahLength.setText(Integer.toString(MorseSettings.getDahLength()));
		lengthOfSpaceBetweenCharacters.setText(Integer.toString(MorseSettings.getLengthOfSpaceBetweenCharacters() / MorseSettings.getDitLength()));
		lengthOfSpaceBetweenWords.setText(Integer.toString(MorseSettings.getLengthOfSpaceBetweenWords() / MorseSettings.getDitLength()));
		lengthOfSpaceBetweenCharElements.setText(Integer.toString(MorseSettings.getLengthOfSpaceBetweenCharElements() / MorseSettings.getDitLength()));
		frequency.setText(Integer.toString(MorseSettings.getFrequency()));
		volume.setText(Float.toString(MorseSettings.getVolume() * 100.0f));

		charPool.getItems().add(String.valueOf(CharPool.englishSet));
		charPool.getItems().add(String.valueOf(CharPool.polishSet));
		charPool.getItems().add(String.valueOf(CharPool.digits));
		charPool.getItems().add(String.valueOf(CharPool.alphanumeric));
		charPool.getItems().add(String.valueOf(CharPool.polishAlphanumeric));
		charPool.getItems().add(String.valueOf(CharPool.symbols));
		charPool.getItems().add(String.valueOf(CharPool.fullCharacterSet));

		charPool.getSelectionModel().select(String.valueOf(MorseSettings.getPool()));
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
		MorseSettings.setPauseBeforeKeying(Integer.parseInt(pauseBeforeKeying.getText()));
		MorseSettings.setLengthOfSpaceBetweenCharacters(Integer.parseInt(lengthOfSpaceBetweenCharacters.getText()) * MorseSettings.getDitLength());
		MorseSettings.setLengthOfSpaceBetweenWords(Integer.parseInt(lengthOfSpaceBetweenWords.getText()) * MorseSettings.getDitLength());
		MorseSettings.setLengthOfSpaceBetweenCharElements(Integer.parseInt(lengthOfSpaceBetweenCharElements.getText()) * MorseSettings.getDitLength());
		MorseSettings.setKeyingSpeed(Integer.parseInt(keyingSpeed.getText()));
		MorseSettings.setDitLength(Integer.parseInt(ditLength.getText()));
		MorseSettings.setDahLength(Integer.parseInt(dahLength.getText()));
		MorseSettings.setFrequency(Integer.parseInt(frequency.getText()));
		MorseSettings.setVolume(Float.parseFloat(volume.getText()) / 100.0f);
		MorseSettings.setPool(charPool.getSelectionModel().getSelectedItem().toCharArray());
		// get a handle to the stage
		Stage stage = (Stage) closeButton.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	@FXML
	private TextField pauseBeforeKeying;

	@FXML
	private TextField keyingSpeed;

	@FXML
	private TextField ditLength;

	@FXML
	private TextField dahLength;

	@FXML
	private TextField lengthOfSpaceBetweenCharacters;

	@FXML
	private TextField lengthOfSpaceBetweenWords;

	@FXML
	private TextField lengthOfSpaceBetweenCharElements;

	@FXML
	private TextField frequency;

	@FXML
	private TextField volume;

	@FXML
	private ComboBox<String> charPool;

	@FXML
	private Button closeButton;
}
