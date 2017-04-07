package pl.hubot.dev.learn_morse;

import javafx.scene.control.Alert;

/**
 * Error handler class.
 */
public class ErrorHandler {
	/**
	 * Handle exception.
	 * @param ex exception
	 */
	public static void handleException(Exception ex) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(ex.getLocalizedMessage());
		alert.showAndWait();
		System.out.println(ex.getMessage());
	}
}
