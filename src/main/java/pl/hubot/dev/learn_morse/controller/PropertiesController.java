package pl.hubot.dev.learn_morse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
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
		pauseBeforeKeying.setValue(settings.getPauseBeforeKeying());
		keyingSpeed.setValue(settings.getKeyingSpeed());
		ditLength.setValue(settings.getDitLength());
		dahLength.setValue(settings.getDahLength());
		lengthOfSpaceBetweenCharacters.setValue(settings.getLengthOfSpaceBetweenCharacters());
		lengthOfSpaceBetweenWords.setValue(settings.getLengthOfSpaceBetweenWords());
		lengthOfSpaceBetweenCharElements.setValue(settings.getLengthOfSpaceBetweenCharElements());
		frequency.setValue(settings.getFrequency());
		volume.setValue(settings.getVolume());
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

	}

	@FXML
	private Slider pauseBeforeKeying;

	@FXML
	private Slider keyingSpeed;

	@FXML
	private Slider ditLength;

	@FXML
	private Slider dahLength;

	@FXML
	private Slider lengthOfSpaceBetweenCharacters;

	@FXML
	private Slider lengthOfSpaceBetweenWords;

	@FXML
	private Slider lengthOfSpaceBetweenCharElements;

	@FXML
	private Slider frequency;

	@FXML
	private Slider volume;

	@FXML
	private Button closeButton;

	private MorseSettings settings = MainController.getSettings();
}
