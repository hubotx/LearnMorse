package pl.hubot.dev.learn_morse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutController {
    @FXML private Button okButton;

    public final void close() {
        // get a handle to the stage
        Stage stage = (Stage) okButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
