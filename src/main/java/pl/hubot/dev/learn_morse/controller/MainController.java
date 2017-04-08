package pl.hubot.dev.learn_morse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pl.hubot.dev.learn_morse.util.ErrorHandler;
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
    /**
     * Input text.
     */
    @FXML private TextArea txtInput;

    /**
     * Output text.
     */
    @FXML private TextArea txtOutput;

    /**
     * Application settings.
     */
    private Settings settings;

    /**
     * Initialize controller.
     */
    public MainController() {
        try {
            settings = Settings.getInstance();
        } catch (IllegalAccessException
                | NoSuchFieldException
                | IOException ex) {
            ErrorHandler.handleException(ex);
        }
    }

    /**
     * Receive Morse code.
     */
    public final void receive() {
        new Thread(() -> {
            try {
                txtOutput.setText(new Encoder().decode(txtInput.getText()));
            } catch (IOException | NoSuchFieldException ex) {
                ErrorHandler.handleException(ex);
            }
        }).start();
    }

    /**
     * Transmit Morse code.
     */
    public final void transmit() {
        new Thread(() -> {
            try {
                new Transmitter().transmit(txtInput.getText());
                txtOutput.setText(new Encoder().encode(txtInput.getText()));
            } catch (LineUnavailableException
                    | IOException
                    | NoSuchFieldException
                    | IllegalAccessException
                    | InterruptedException ex) {
                ErrorHandler.handleException(ex);
            }
        }).start();
    }

    /**
     * Perform knowledge training of Morse.
     */
    public final void train() {
        new Thread(() -> {
            try {
                char[] pool = settings.getPool().toCharArray();
                StringBuilder randomCharacters = new StringBuilder();
                final byte words = 5;
                final byte chars = 4;
                for (int wordIndex = 0; wordIndex < words; wordIndex++) {
                    for (int charIndex = 0; charIndex < chars; charIndex++) {
                        char curr = pool[new Random().nextInt(pool.length)];
                        randomCharacters.append(curr);
                    }
                    randomCharacters.append(' ');
                }
                new Transmitter().transmit(randomCharacters.toString());
            } catch (LineUnavailableException
                    | NoSuchFieldException
                    | IOException
                    | IllegalAccessException
                    | InterruptedException ex) {
                ErrorHandler.handleException(ex);
            }
        }).start();
    }

    /**
     * Allow user to modify application properties.
     *
     * @throws IOException caused by getClass().getResource(...)
     */
    public final void changeProperties() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                getClass().getResource("../../../../../view/Properties.fxml"));
        stage.setTitle("Properties");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
