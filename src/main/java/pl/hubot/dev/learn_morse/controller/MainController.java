package pl.hubot.dev.learn_morse.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                | IOException
                | EngineException
                | PropertyVetoException
                | AudioException ex) {
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

    public final void train() {
        ExecutorService executor = Executors.newFixedThreadPool(1, runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        executor.execute(() -> {
            try {
                transceiver.blocksMethod();
            } catch (LineUnavailableException
                    | NoSuchFieldException
                    | IOException
                    | IllegalAccessException
                    | InterruptedException ex) {
                ErrorHandler.handleException(ex);
            }
        });
        executor.execute(() -> Platform.runLater(() -> {
            final int delay = 3000;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                ErrorHandler.handleException(e);
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you ready?");
            alert.setContentText("Click OK to verify.");
            alert.showAndWait();
            if (txtInput.getText().equals(transceiver.getTransmitted())) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success");
                alert.setContentText("You're right!");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Fail");
                alert.setContentText("You failed!");
                alert.showAndWait();
            }
        }));
        executor.shutdown();
        final int millis = 100;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(transceiver.getTransmitted());
    }

    public final void changeProperties() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                getClass().getResource("../../../../../view/Properties.fxml"));
        stage.setTitle("Properties");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
