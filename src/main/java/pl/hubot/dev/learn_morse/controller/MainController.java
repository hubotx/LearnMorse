package pl.hubot.dev.learn_morse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import pl.hubot.dev.learn_morse.BlocksTrainer;
import pl.hubot.dev.learn_morse.KochTrainer;
import pl.hubot.dev.learn_morse.Trainer;
import pl.hubot.dev.learn_morse.model.Transceiver;
import pl.hubot.dev.learn_morse.util.ErrorHandler;
import pl.hubot.dev.learn_morse.model.Encoder;

import javax.sound.sampled.LineUnavailableException;
import javax.speech.AudioException;
import javax.speech.EngineException;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for resource view/Main.fxml.
 */
public class MainController implements Initializable {
    @FXML private TextArea txtInput;
    @FXML private TextArea txtOutput;

    private Transceiver transceiver;

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        try {
            transceiver = new Transceiver();
        } catch (IllegalAccessException
                | NoSuchFieldException
                | IOException ex) {
            ErrorHandler.handleException(ex);
        }
    }

    public final void receive() {
        new Thread(() -> {
            try {
                txtOutput.setText(transceiver.receive(txtInput.getText()));
            } catch (IOException
                    | NoSuchFieldException
                    | InterruptedException
                    | EngineException
                    | AudioException
                    | PropertyVetoException ex) {
                ErrorHandler.handleException(ex);
            }
        }).start();
    }

    public final void transmit() {
        new Thread(() -> {
            try {
                transceiver.transmit(txtInput.getText());
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

    public final void trainUsingBlocksMethod() {
        Trainer trainer = new BlocksTrainer();
        trainer.train();
        trainer.verifySkills(txtInput);
    }

    public final void trainUsingKochMethod() {
        Trainer trainer = new KochTrainer();
        trainer.train();
        trainer.verifySkills(txtInput);
    }

    public final void changeProperties() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                getClass().getResource("../../../../../view/Properties.fxml"));
        stage.setTitle("Properties");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public final void displayAbout() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                getClass().getResource("../../../../../view/About.fxml"));
        stage.setTitle("About");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
