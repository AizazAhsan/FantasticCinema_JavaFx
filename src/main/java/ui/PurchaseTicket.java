package ui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import database.Database;
import model.User;
import model.Room;
import model.Movie;

import java.util.Optional;

public class PurchaseTicket extends MainWindow {

    private Stage window;
    private Database database;
    private User user;

    private Label lblSelectedMovieTitle;
    private ComboBox cmbNoOfTickets;

    private TextField txtCustomerName;
    private Label lblMovieStartTime ;
    private Label lblMovieEndTime;
    private Label lblMovieRoom;
    private Button btnClear;
    private Button btnPurchase;
    private ToggleButton btnFilter;  // for assignment1 retake exam

    private GridPane gridPane2;

    public PurchaseTicket(User user, Database database) {
        this.database = database;
        this.user = user;

        lblMovieRoom = new Label();
        cmbNoOfTickets = new ComboBox();
        lblMovieStartTime = new Label();
        lblMovieEndTime = new Label();
        lblSelectedMovieTitle = new Label();
        txtCustomerName = new TextField();
        btnPurchase = new Button("Purchase");
        btnClear = new Button("Clear");
        btnFilter = new ToggleButton("Show only today");
        btnClear.setMaxWidth(200);

        gridPane2 = new GridPane();

        //setting up the window
        window = new Stage();
        window.setWidth(1000);
        window.setHeight(600);
        window.setTitle("Fantastic Cinema -- -- purchase tickets -- username: " + user.getUserName());

        //this method sets up menu and menubar and checks which UserRole we receive for the user and prints the correct menubar
        adminMenu.getItems().addAll(manageShowingsMenuItem, manageMoviesMenuItem);
        menubar = createMenuAndMenuBar(user);

        //this method creates the middle gridPane that contains the formName and two tableviews for room1 and room2
        //getting data from the database
        tableViewRoom1.setItems(database.getRoom1());
        tableViewRoom2.setItems(database.getRoom2());
        gridPane = createAndFillGridPane1();

        //this method will create the bottom gridPane that contains all the label,textFile,combobox and buttons
        //information about selected movie will display here
        this.gridPane2 = makeAndFillGridPane2();

        BorderPane borderpane = new BorderPane();
        borderpane.setTop(menubar);
        borderpane.setCenter(gridPane);
        borderpane.setBottom(gridPane2);
        Scene scene = new Scene(borderpane);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.show();

        //this event is called when a movie is selected in room 1 and fills the information in the proper label
        tableViewRoom1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //this method will display information about selected movie in the tableview information
                Room room = tableViewRoom1.getSelectionModel().getSelectedItem();
                fillMovieInformation(room);
                lblMovieRoom.setText("Room 1");
            }
        });

        //this event is called when a movie is selected in room 2 and fills the information in the proper label
        tableViewRoom2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //this method will display information about selected movie in the tableview information
                Room room = tableViewRoom2.getSelectionModel().getSelectedItem();
                fillMovieInformation(room);
                lblMovieRoom.setText("Room 2");
            }
        });

        //this event is called when we want to clear the selected data through button clear
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                txtCustomerName.clear();
                gridPane2.setVisible(false);
            }
        });

        //this event will work to log out, and it will send the correct data until the system is shutdown
        //this will help us to prevent data lose while the app is running
        logoutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new LoginScreenWindow().start(database);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                window.close();

            }
        });

        //this method will call manage showing window and sends as parameter database and user in order to prevent data loss
        manageShowingsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new ShowingsManagementWindow(user,database);
                window.close();
            }
        });

        //this method will call manage movies window and sends as parameter database and user in order to prevent data loss
        manageMoviesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new MovieManagementWindow(database,user);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                window.close();
            }
        });

        //this event will deduct number of seats which are bought according their room number and movie title
        btnPurchase.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                Movie movie1 = new Movie();
                movie1.setAgeRestriction(17);

                if (!txtCustomerName.getText().equals("") && !cmbNoOfTickets.getValue().equals(0)) {
                    a.setTitle("Purchase");
                    a.setContentText("Are you sure you confirm your purchase?");
                    if (lblMovieRoom.getText().equals("Room 1")) {

                        //this method will check the right movie and deduct the purchased number of tickets
                        checkPurchaseAndDeductSeats(database.getRoom1(),a);
                    }
                    else if (lblMovieRoom.getText().equals("Room 2")) {

                        //this method will check the right movie and deduct the purchased number of tickets
                        checkPurchaseAndDeductSeats(database.getRoom2(),a);
                    }
                }
                else {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setContentText("Check the name or No. of tickets");
                    a.show();
                }
            }
        });

        btnFilter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }

    //this method sets up menu and menubar and check which UserRole we receive for the user and prints the correct menubar

    //this method will make the bottom gridPane that contains all the label,textFile,combobox and buttons
    //information about selected movie displayed here
    private  GridPane makeAndFillGridPane2(){
        //adding label, textField, and buttons
        Label lblRoom = new Label("Room");
        GridPane.setConstraints(lblRoom, 0, 0);
        Label lblStartTime = new Label("Start");
        GridPane.setConstraints(lblStartTime, 0, 1);
        Label lblEndTime = new Label("End");
        GridPane.setConstraints(lblEndTime, 0, 2);
        Label lblMovieTitle = new Label("Movie Title");
        GridPane.setConstraints(lblMovieTitle, 2, 0);
        Label lblNoOfSeats = new Label("No. of seats");
        GridPane.setConstraints(lblNoOfSeats, 2, 1);
        Label lblNameOfCustomer = new Label("Name");
        GridPane.setConstraints(lblNameOfCustomer, 2, 2);
        GridPane.setConstraints(btnPurchase, 4, 1);
        GridPane.setConstraints(btnClear, 4, 2);
        GridPane.setConstraints(btnFilter,10,0);

        //the part which is filled with information of selected movie

        GridPane.setConstraints(lblMovieRoom, 1, 0);
        GridPane.setConstraints(lblMovieStartTime, 1, 1);
        GridPane.setConstraints(lblMovieEndTime, 1, 2);
        GridPane.setConstraints(lblSelectedMovieTitle, 3, 0);
        txtCustomerName.setPromptText("Name");
        GridPane.setConstraints(txtCustomerName, 3, 2);
        GridPane.setConstraints(cmbNoOfTickets, 3, 1);


        //making the second gridPane for the labels that show selected movie information
        gridPane2.setVisible(false);
        gridPane2.minHeight(400);
        gridPane2.setPadding(new Insets(20, 20, 20, 20));
        gridPane2.setHgap(20);
        gridPane2.setVgap(20);
        gridPane2.getChildren().addAll(btnPurchase, btnClear, btnFilter, lblMovieTitle, lblRoom, lblEndTime, lblNameOfCustomer, lblStartTime, lblNoOfSeats);
        gridPane2.getChildren().addAll(cmbNoOfTickets, txtCustomerName, lblSelectedMovieTitle, lblMovieEndTime, lblMovieStartTime, lblMovieRoom);

        return gridPane2;
    }

    //this method checks the right movie and deduct the purchased number of tickets
    public void checkPurchaseAndDeductSeats(ObservableList<Room> rooms, Alert a){
        for (Room r : rooms) {
            if (r.getTitle().equals(lblSelectedMovieTitle.getText()) && r.getStartTimeMovie().equals(lblMovieStartTime.getText())) {
                Optional<ButtonType> result = a.showAndWait();
                if (result.get()==ButtonType.OK) {
                    r.setSeats(r.getSeats() - (int) cmbNoOfTickets.getValue());
                    tableViewRoom1.refresh();
                    tableViewRoom2.refresh();
                    txtCustomerName.clear();
                    gridPane2.setVisible(false);
                } else if(result.get()==ButtonType.CANCEL){
                    return;
                }
            }
        }
    }

    //this method will display information about selected movie in the tableview information
    public void fillMovieInformation(Room room){
        txtCustomerName.clear();
        gridPane2.setVisible(true);
        lblMovieStartTime.setText(room.getStartTimeMovie());
        lblMovieEndTime.setText(room.getEndTimeMovie());
        lblSelectedMovieTitle.setText(room.getMovie().getTitle());
        cmbNoOfTickets.getItems().clear();
        for (int i = 1; i < room.getSeats() + 1; i++) {
            cmbNoOfTickets.getItems().addAll(i);
        }
        cmbNoOfTickets.setValue(0);
    }
}
