package pl.hubot.dev.learn_morse.util;

import javafx.scene.control.Alert;

/**
 * Error handler class.
 */
public final class ErrorHandler {
    /**
     * Constructor.
     */
    private ErrorHandler() {
    }

    /**
     * Handle exception.
     *
     * @param ex exception
     */
    public static void handleException(final Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(ex.getLocalizedMessage());
        alert.showAndWait();
        System.out.println(ex.getMessage());
    }
}
