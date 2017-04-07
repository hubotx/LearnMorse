package pl.hubot.dev.learn_morse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pl.hubot.dev.learn_morse.ErrorHandler;
import pl.hubot.dev.learn_morse.model.Encoder;
import pl.hubot.dev.learn_morse.model.Transmitter;
import pl.hubot.dev.learn_morse.model.Settings;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.Random;

/**
 * Controller class for resource view/Main.fxml.
 */
public class MainController {
	@FXML public TextArea txt_input;
	@FXML public TextArea txt_output;

	private Settings settings;

	/**
	 * Initialize controller.
	 */
	public MainController() {
		try {
			settings = Settings.getInstance();
		} catch (IllegalAccessException | NoSuchFieldException | IOException ex) {
			ErrorHandler.handleException(ex);
		}
	}

	/**
	 * Receive Morse code.
	 */
	public void receive() {
		new Thread(() -> {
			try {
				txt_output.setText(new Encoder().decode(txt_input.getText()));
			} catch (IOException | NoSuchFieldException ex) {
				ErrorHandler.handleException(ex);
			}
		}).start();
	}

	/**
	 * Transmit Morse code.
	 */
	public void transmit() {
		new Thread(() -> {
			try {
				new Transmitter().transmit(txt_input.getText());
				txt_output.setText(new Encoder().encode(txt_input.getText()));
			} catch (LineUnavailableException | IOException | NoSuchFieldException | IllegalAccessException | InterruptedException ex) {
				ErrorHandler.handleException(ex);
			}
		}).start();
	}

	/**
	 * Perform knowledge training of Morse.
	 */
	public void train() {
		new Thread(() -> {
			try {
				char[] pool = settings.getPool().toCharArray();
				StringBuilder randomCharacters = new StringBuilder();
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 4; j++) {
						char curr = pool[new Random().nextInt(pool.length)];
						randomCharacters.append(curr);
					}
					randomCharacters.append(' ');
				}
				new Transmitter().transmit(randomCharacters.toString());
				// TODO: implement checking results of training
			}
			catch (LineUnavailableException | NoSuchFieldException | IOException | IllegalAccessException | InterruptedException ex) {
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