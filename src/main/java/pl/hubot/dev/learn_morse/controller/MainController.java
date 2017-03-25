package pl.hubot.dev.learn_morse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pl.hubot.dev.learn_morse.model.MorseSettings;
import pl.hubot.dev.learn_morse.model.MorseString;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Controller class for resource view/Main.fxml.
 */
public class MainController implements Initializable {
	/**
	 * Initialize controller.
	 * @param location location
	 * @param resources resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MorseString.setSettings(new MorseSettings(2, 4, 800));
	}

	/**
	 * Receive Morse code.
	 */
	public void receive(ActionEvent event) {
		new Thread(() -> {
			MorseString.receive(txt_input.getText());
			txt_output.setText(MorseString.getDecoded());
		}).start();
	}

	/**
	 * Transmit Morse code.
	 * @throws LineUnavailableException caused by sound(...) method
	 * @throws InterruptedException caused by Thread.Sleep(...)
	 */
	public void transmit(ActionEvent event) throws LineUnavailableException, InterruptedException {
		new Thread(() -> {
			try {
				MorseString.getSettings().setMuted(true);
				MorseString.transmit(txt_input.getText());
				MorseString.getSettings().setMuted(false);
			} catch (Exception ex) {

			}
			txt_output.setText(MorseString.getEncoded());
		}).start();
	}

	/**
	 * Perform knowledge training of Morse.
	 * @throws LineUnavailableException caused by sound(...) method
	 * @throws InterruptedException caused by Thread.Sleep(...)
	 */
	public void train(ActionEvent event) throws LineUnavailableException, InterruptedException {
		new Thread(() -> {
			try {
				char[] pool = MorseString.getSettings().getPool();
				StringBuilder randomCharacters = new StringBuilder();
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 4; j++) {
						char curr = pool[new Random().nextInt(pool.length)];
						randomCharacters.append(curr);
					}
					randomCharacters.append(' ');
				}
				MorseString.transmit(randomCharacters.toString());
				txt_output.setText(MorseString.getEncoded());
			}
			catch (Exception ex) {

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

	@FXML public MenuItem btn_transmit;
	@FXML public TextArea txt_input;
	@FXML public TextArea txt_output;
}