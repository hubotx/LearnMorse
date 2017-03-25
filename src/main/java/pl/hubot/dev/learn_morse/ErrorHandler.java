package pl.hubot.dev.learn_morse;

import javafx.scene.control.Alert;

public class ErrorHandler {
	public static void handleException(Exception ex) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(ex.getLocalizedMessage());
		alert.showAndWait();
	}
}
