package com.example.javafundamentals_endassignment;

import database.Database;
import javafx.application.Application;
import javafx.stage.Stage;
import model.User;
import ui.LoginScreenWindow;
import ui.MovieManagementWindow;
import ui.ShowingsManagementWindow;

public class Main extends Application {

    private Database database = new Database();
    private User user = new User();

    @Override
    public void start(Stage stage) {
        MovieManagementWindow movieManagementWindow = new MovieManagementWindow(database, user);
        movieManagementWindow.getStage().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
