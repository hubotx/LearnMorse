package pl.hubot.dev.learn_morse.controller;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.hubot.dev.learn_morse.Main;
import pl.hubot.dev.learn_morse.model.MorseSettings;

import java.net.URL;
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
		pauseBeforeKeying.setText(Integer.toString(settings.getPauseBeforeKeying()));
		keyingSpeed.setText(Integer.toString(settings.getKeyingSpeed()));
		ditLength.setText(Integer.toString(settings.getDitLength()));
		dahLength.setText(Integer.toString(settings.getDahLength()));
		lengthOfSpaceBetweenCharacters.setText(Integer.toString(settings.getLengthOfSpaceBetweenCharacters() / settings.getDitLength()));
		lengthOfSpaceBetweenWords.setText(Integer.toString(settings.getLengthOfSpaceBetweenWords() / settings.getDitLength()));
		lengthOfSpaceBetweenCharElements.setText(Integer.toString(settings.getLengthOfSpaceBetweenCharElements() / settings.getDitLength()));
		frequency.setText(Integer.toString(settings.getFrequency()));
		volume.setText(Float.toString(settings.getVolume() * 100.0f));
	}

	/**
	 * Close window.
	 * @param event event
	 */
	public void cancel(ActionEvent event) {
		// get a handle to the stage
		Stage stage = (Stage) closeButton.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	/**
	 * Apply changes.
	 * @param event event
	 */
	public void ok(ActionEvent event) {
		MainController.setSettings(new MorseSettings(
				Integer.parseInt(pauseBeforeKeying.getText()),
				Integer.parseInt(lengthOfSpaceBetweenCharacters.getText()) * settings.getDitLength(),
				Integer.parseInt(lengthOfSpaceBetweenWords.getText()) * settings.getDitLength(),
				Integer.parseInt(lengthOfSpaceBetweenCharElements.getText()) * settings.getDitLength(),
				Integer.parseInt(keyingSpeed.getText()),
				Integer.parseInt(ditLength.getText()),
				Integer.parseInt(dahLength.getText()),
				Integer.parseInt(frequency.getText()),
				Float.parseFloat(volume.getText()) / 100.0f
				));
		MainController.applySettings();
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
	private Button closeButton;

	private MorseSettings settings = MainController.getSettings();
}
