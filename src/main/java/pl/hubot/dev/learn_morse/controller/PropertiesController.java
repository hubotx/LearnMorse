package pl.hubot.dev.learn_morse.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.hubot.dev.learn_morse.util.ErrorHandler;
import pl.hubot.dev.learn_morse.model.CharPool;
import pl.hubot.dev.learn_morse.util.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML private TextField lengthOfStrings;
    @FXML private TextField numberOfStrings;
    @FXML private TextField kochCharsToSend;

    private Settings settings = Settings.getInstance();
    private final float maxVolume = 100.0f;

    public PropertiesController()
            throws IllegalAccessException,
            NoSuchFieldException,
            IOException {
    }

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
            pauseBeforeKeying.setText(String.valueOf(settings.getPauseBeforeKeying()));
            keyingSpeed.setText(String.valueOf(settings.getKeyingSpeed()));
            dotLength.setText(String.valueOf(settings.getDotLength()));
            dashLength.setText(String.valueOf(settings.getDashLength()));
            lengthOfSpaceBetweenCharacters.setText(String.valueOf(
                    settings.getLengthOfSpaceBetweenCharacters() / settings.getDotLength()));
            lengthOfSpaceBetweenWords.setText(String.valueOf(
                    settings.getLengthOfSpaceBetweenWords() / settings.getDotLength()));
            lengthOfSpaceBetweenCharElements.setText(String.valueOf(
                    settings.getLengthOfSpaceBetweenCharElements() / settings.getDotLength()));
            frequency.setText(String.valueOf(settings.getFrequency()));
            volume.setText(String.valueOf(Float.parseFloat(String.valueOf(settings.getVolume() * maxVolume))));
            lengthOfStrings.setText(String.valueOf(settings.getBlocksLengthOfStrings()));
            numberOfStrings.setText(String.valueOf(settings.getBlocksStringsToSend()));
            kochCharsToSend.setText(String.valueOf(settings.getKochCharsToSend()));

            charPool.getItems().add(String.valueOf(CharPool.ENGLISH_SET));
            charPool.getItems().add(String.valueOf(CharPool.POLISH_SET));
            charPool.getItems().add(String.valueOf(CharPool.DIGITS));
            charPool.getItems().add(String.valueOf(CharPool.ALPHANUMERIC));
            charPool.getItems().add(String.valueOf(CharPool.POLISH_ALPHANUMERIC));
            charPool.getItems().add(String.valueOf(CharPool.SYMBOLS));
            charPool.getItems().add(String.valueOf(CharPool.FULL_CHARACTER_SET));

            charPool.getSelectionModel().select(String.valueOf(settings.getPool()));
    }

    public final void cancel() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public final void ok() {
        try {
            // set properties
            settings.setPauseBeforeKeying(Integer.parseInt(pauseBeforeKeying.getText()));
            settings.setLengthOfSpaceBetweenCharacters(
                    Integer.parseInt(lengthOfSpaceBetweenCharacters.getText()) * settings.getDotLength());
            settings.setLengthOfSpaceBetweenWords(
                    Integer.parseInt(lengthOfSpaceBetweenWords.getText()) * settings.getDotLength());
            settings.setLengthOfSpaceBetweenCharElements(
                    Integer.parseInt(lengthOfSpaceBetweenCharElements.getText()) * settings.getDotLength());
            settings.setKeyingSpeed(Integer.parseInt(keyingSpeed.getText()));
            settings.setDotLength(Integer.parseInt(dotLength.getText()));
            settings.setDashLength(Integer.parseInt(dashLength.getText()));
            settings.setFrequency(Integer.parseInt(frequency.getText()));
            settings.setVolume(Float.parseFloat(volume.getText()) / maxVolume);
            settings.setPool(charPool.getSelectionModel().getSelectedItem());
            settings.setBlocksLengthOfStrings(Integer.parseInt(lengthOfStrings.getText()));
            settings.setBlocksStringsToSend(Integer.parseInt(numberOfStrings.getText()));
            settings.setKochCharsToSend(Integer.parseInt(kochCharsToSend.getText()));
            settings.apply();
        } catch (IllegalAccessException | NumberFormatException ex) {
            ErrorHandler.handleException(ex);
        } finally {
            // get a handle to the stage
            Stage stage = (Stage) closeButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }
}
