package pl.hubot.dev.learn_morse;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import pl.hubot.dev.learn_morse.model.Transceiver;
import pl.hubot.dev.learn_morse.util.ErrorHandler;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Trainer {
    public abstract void train();

    public final void verifySkills(String input) {
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
            if (input.equals(transceiver.getTransmitted())) {
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
    }

    final void initialize() {
        executor = Executors.newFixedThreadPool(1, runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
    }

    static ExecutorService executor;
    static Transceiver transceiver;

    static {
        try {
            transceiver = new Transceiver();
        } catch (IllegalAccessException
                | NoSuchFieldException
                | IOException e) {
            ErrorHandler.handleException(e);
        }
    }
}
