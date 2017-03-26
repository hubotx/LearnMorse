package pl.hubot.dev.learn_morse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pl.hubot.dev.learn_morse.ErrorHandler;
import pl.hubot.dev.learn_morse.model.MorseCode;
import pl.hubot.dev.learn_morse.model.MorseSettings;
import pl.hubot.dev.learn_morse.model.MorseString;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.Random;

/**
 * Controller class for resource view/Main.fxml.
 */
public class MainController {
	@FXML public TextArea txt_input;
	@FXML public TextArea txt_output;

	/**
	 * Initialize controller.
	 */
	public MainController() {
		try {
			MorseSettings.load();
		} catch (IOException ex) {
			ErrorHandler.handleException(ex);
		}
	}

	/**
	 * Receive Morse code.
	 */
	public void receive() {
		new Thread(() -> txt_output.setText(MorseCode.decode(txt_input.getText()))).start();
	}

	/**
	 * Transmit Morse code.
	 * @throws LineUnavailableException caused by sound(...) method
	 * @throws InterruptedException caused by Thread.Sleep(...)
	 */
	public void transmit() throws LineUnavailableException, InterruptedException {
		new Thread(() -> {
			try {
				MorseString.transmit(txt_input.getText());
			} catch (LineUnavailableException | InterruptedException ex) {
				ErrorHandler.handleException(ex);
			}
			txt_output.setText(MorseCode.encode(txt_input.getText()));
		}).start();
	}

	/**
	 * Perform knowledge training of Morse.
	 * @throws LineUnavailableException caused by sound(...) method
	 * @throws InterruptedException caused by Thread.Sleep(...)
	 */
	public void train() throws LineUnavailableException, InterruptedException {
		new Thread(() -> {
			try {
				char[] pool = MorseSettings.getProperties().getProperty("pool").toCharArray();
				StringBuilder randomCharacters = new StringBuilder();
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 4; j++) {
						char curr = pool[new Random().nextInt(pool.length)];
						randomCharacters.append(curr);
					}
					randomCharacters.append(' ');
				}
				MorseString.transmit(randomCharacters.toString());
				// TODO: implement checking results of training
			}
			catch (LineUnavailableException | InterruptedException ex) {
				ErrorHandler.handleException(ex);
			}
		}).start();
	}

	/**
	 * Allow user to modify application properties.
	 * @throws IOException caused by getClass().getResource(...)
	 */
	public void changeProperties() throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../../../../../view/Properties.fxml"));
		stage.setTitle("Properties");
		stage.setScene(new Scene(root));
		stage.show();
	}
}