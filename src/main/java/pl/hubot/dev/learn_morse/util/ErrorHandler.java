package pl.hubot.dev.learn_morse.util;

import javafx.scene.control.Alert;

public final class ErrorHandler {
    private ErrorHandler() {
    }

    public static void handleException(final Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(ex.getLocalizedMessage());
        alert.showAndWait();
        System.out.println(ex.getMessage());
    }
}
