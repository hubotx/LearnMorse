package pl.hubot.dev.learn_morse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.hubot.dev.learn_morse.util.ErrorHandler;
import pl.hubot.dev.learn_morse.model.CharPool;
import pl.hubot.dev.learn_morse.model.Settings;

import java.io.IOException;

/**
 * Controller class for resource view/Properties.fxml.
 */
public class PropertiesController {
    /**
     * Pause before keying.
     */
    @FXML
    private TextField pauseBeforeKeying;

    /**
     * Keying speed.
     */
    @FXML
    private TextField keyingSpeed;

    /**
     * Dot length.
     */
    @FXML
    private TextField dotLength;

    /**
     * Dash length.
     */
    @FXML
    private TextField dashLength;

    /**
     * Length of space between characters.
     */
    @FXML
    private TextField lengthOfSpaceBetweenCharacters;

    /**
     * Length of space between words.
     */
    @FXML
    private TextField lengthOfSpaceBetweenWords;

    /**
     * Length of space between char elements.
     */
    @FXML
    private TextField lengthOfSpaceBetweenCharElements;

    /**
     * Frequency.
     */
    @FXML
    private TextField frequency;

    /**
     * Volume.
     */
    @FXML
    private TextField volume;

    /**
     * Character pool.
     */
    @FXML
    private ComboBox<String> charPool;

    /**
     * Close button.
     */
    @FXML
    private Button closeButton;

    /**
     * Settings.
     */
    private Settings settings = Settings.getInstance();

    /**
     * Initialize controller.
     *
     * @throws IllegalAccessException IllegalAccessException
     * @throws NoSuchFieldException   NoSuchFieldException
     * @throws IOException            IOException
     */
    public PropertiesController()
            throws IllegalAccessException,
            NoSuchFieldException,
            IOException {
        pauseBeforeKeying.setText(String.valueOf(
                settings.getPauseBeforeKeying()));
        keyingSpeed.setText(String.valueOf(
                settings.getKeyingSpeed()));
        dotLength.setText(String.valueOf(
                settings.getDotLength()));
        dashLength.setText(String.valueOf(
                settings.getDashLength()));
        lengthOfSpaceBetweenCharacters.setText(String.valueOf(
                settings.getLengthOfSpaceBetweenCharacters()
                        / settings.getDotLength()));
        lengthOfSpaceBetweenWords.setText(String.valueOf(
                settings.getLengthOfSpaceBetweenWords()
                        / settings.getDotLength()));
        lengthOfSpaceBetweenCharElements.setText(String.valueOf(
                settings.getLengthOfSpaceBetweenCharElements()
                        / settings.getDotLength()));
        frequency.setText(String.valueOf(settings.getFrequency()));
        volume.setText(String.valueOf(
                Float.parseFloat(
                        String.valueOf(settings.getVolume()
                                * maxVolume))));

        charPool.getItems().add(
                String.valueOf(CharPool.ENGLISH_SET));
        charPool.getItems().add(
                String.valueOf(CharPool.POLISH_SET));
        charPool.getItems().add(
                String.valueOf(CharPool.DIGITS));
        charPool.getItems().add(
                String.valueOf(CharPool.ALPHANUMERIC));
        charPool.getItems().add(
                String.valueOf(CharPool.POLISH_ALPHANUMERIC));
        charPool.getItems().add(
                String.valueOf(CharPool.SYMBOLS));
        charPool.getItems().add(
                String.valueOf(CharPool.FULL_CHARACTER_SET));

        charPool.getSelectionModel()
                .select(String.valueOf(settings.getPool()));
    }

    /**
     * Cancel editing properties.
     */
    public final void cancel() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    /**
     * Apply changes.
     */
    public final void ok() {
        try {
            // set properties
            settings.setPauseBeforeKeying(
                    Integer.parseInt(pauseBeforeKeying.getText()));
            settings.setLengthOfSpaceBetweenCharacters(
                    Integer.parseInt(
                            lengthOfSpaceBetweenCharacters
                                    .getText())
                            * settings.getDotLength());
            settings.setLengthOfSpaceBetweenWords(
                    Integer.parseInt(
                            lengthOfSpaceBetweenWords
                                    .getText())
                            * settings.getDotLength());
            settings.setLengthOfSpaceBetweenCharElements(
                    Integer.parseInt(
                            lengthOfSpaceBetweenCharElements
                                    .getText())
                            * settings.getDotLength());
            settings.setKeyingSpeed(
                    Integer.parseInt(keyingSpeed.getText()));
            settings.setDotLength(
                    Integer.parseInt(dotLength.getText()));
            settings.setDashLength(
                    Integer.parseInt(dashLength.getText()));
            settings.setFrequency(
                    Integer.parseInt(frequency.getText()));
            settings.setVolume(Float.parseFloat(
                    volume.getText()) / maxVolume);
            settings.setPool(charPool.getSelectionModel()
                    .getSelectedItem());
            settings.apply();
        } catch (IllegalAccessException | NumberFormatException ex) {
            ErrorHandler.handleException(ex);
        } finally {
            // get a handle to the stage
            Stage stage = (Stage) closeButton.getScene()
                    .getWindow();
            // do what you have to do
            stage.close();
        }
    }

    /**
     * Maximum volume (100%).
     */
    private final float maxVolume = 100.0f;
}
