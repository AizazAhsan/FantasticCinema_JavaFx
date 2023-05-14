package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;
import database.Database;

public class LoginScreenWindow extends Application {
    Database database;
    Stage stage;
    private int numberOfAttempts = 3;

    @Override
    public void start(Stage stage) throws Exception {
        database= new Database();
        makeLogInForm(database,stage);
    }

    public void start(Database database){
        this.database = database;
        makeLogInForm(database,new Stage());
    }

    public Stage getStage() {
        return stage;
    }

    public  void  makeLogInForm(Database database, Stage stage){
        //creating the stage
        stage.setHeight(200);
        stage.setWidth(350);
        stage.setTitle("Fabulous Cinema -- Login");
        stage.show();

        //creating the grid
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        //creating scene then grid-->scene-->stage
        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);

        Label lblUserName = new Label("Username");
        GridPane.setConstraints(lblUserName,0,0);

        TextField txtUserInput = new TextField();
        txtUserInput.setPromptText("username");
        GridPane.setConstraints(txtUserInput, 1, 0);

        Label lblPassword = new Label("Password");
        GridPane.setConstraints(lblPassword,0,1);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        GridPane.setConstraints(passwordField, 1, 1);

        Button btnLogin = new Button("Log in");
        GridPane.setConstraints(btnLogin, 1, 2);

        Label lblErrorMessage = new Label();
        GridPane.setConstraints(lblErrorMessage,1,3);

        gridPane.getChildren().addAll(lblUserName,lblPassword,txtUserInput,passwordField,btnLogin,lblErrorMessage);

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                User user = checkCredentials(txtUserInput.getText(),passwordField.getText());
                if ( user != null ){
                    new PurchaseTicket(user,database);
                    stage.close();
                }
                else {
                    lblErrorMessage.setTextFill(Color.web("#ff0000", 0.8));
                    lblErrorMessage.setText("Bad Credentials");
                }
            }
        });
    }

    public User checkCredentials(String userName, String password){
        for(User p:database.getUsers()){
            if (userName.equals(p.getUserName())){
                if(password.equals(p.getPassword())){
                    return p;
                }
            }
            if (password.equals(numberOfAttempts)){
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Exception");
                a.setContentText("Your account has been locked");
            }
        }
        return  null;
    }
}
