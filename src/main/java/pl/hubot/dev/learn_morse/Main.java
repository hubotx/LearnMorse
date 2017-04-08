/*
Copyright (C) 2017 by Hubot. All rights reserved.
Released under the terms of the GNU GPL version 2 or later.
*/
package pl.hubot.dev.learn_morse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.hubot.dev.learn_morse.util.ErrorHandler;

import java.io.IOException;

/**
 * Start-up class of program.
 */
public class Main extends Application {
    /**
     * Start JavaFX application and show main window.
     *
     * @param primaryStage primary stage
     */
    @Override
    public final void start(final Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("../../../../view/Main.fxml"));
            primaryStage.setTitle("Learn Morse");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException ex) {
            ErrorHandler.handleException(ex);
        }
    }

    /**
     * Entry point of this application.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
