package pl.hubot.dev.learn_morse;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import pl.hubot.dev.learn_morse.model.Transceiver;
import pl.hubot.dev.learn_morse.util.ErrorHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Trainer {
    public abstract void train();

    public final void verifySkills(final TextArea input) {
        executor.execute(() -> Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you ready?");
            alert.setContentText("Click OK to verify.");
            alert.showAndWait();
            List<Character> passedCharacters = new ArrayList<>();
            char[] allCharacters = transceiver.getTransmitted().toCharArray();
            for (int i = 0; i < input.getText().length(); i++) {
                char curr = input.getText().charAt(i);
                if (curr == allCharacters[i]) {
                    passedCharacters.add(curr);
                }
            }
            float passedPercent = 100.0f * passedCharacters.size() / allCharacters.length;
            System.out.println(passedCharacters);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Results");
            alert.setContentText("You have passed training with result " + passedPercent + "%.");
            alert.showAndWait();
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
