package ui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Room;
import model.User;
import model.UserRole;

public class MainWindow {
    protected Stage stage;
    protected MenuBar menubar;
    protected MenuItem logoutMenuItem;
    protected MenuItem helpMenuItem;
    protected MenuItem manageShowingsMenuItem;
    protected MenuItem manageMoviesMenuItem;
    protected Menu adminMenu;
    protected Menu helpMenu;
    protected Menu logoutMenu;
    protected TableView<Room> tableViewRoom1;
    protected TableView<Room> tableViewRoom2;
    protected GridPane gridPane;
    protected MenuItem purchaseTicketMenuItem;

    public Stage getStage(){
        return stage;
    }

    public MainWindow(){
        menubar= new MenuBar();
        logoutMenuItem = new MenuItem("Logout");
        helpMenuItem = new MenuItem("About");
        manageShowingsMenuItem = new MenuItem("Manage showings");
        manageMoviesMenuItem = new MenuItem("Manage movies");
        purchaseTicketMenuItem = new MenuItem("Purchase ticket");
        adminMenu = new Menu("Admin");
        helpMenu = new Menu("Help");
        logoutMenu = new Menu("Logout");
        gridPane = new GridPane();
        tableViewRoom1 = new TableView();
        tableViewRoom2 = new TableView();
    }

    public MenuBar createMenuAndMenuBar(User user){
        //creating menu and adding it to menubar
        logoutMenu.getItems().add(logoutMenuItem);
        helpMenu.getItems().add(helpMenuItem);

        //checking the user UserRole and showing the correct menubar
        if (UserRole.User.equals((user.getUserRole()))) {
            menubar = new MenuBar(helpMenu, logoutMenu);
        }
        else {
            menubar = new MenuBar(adminMenu, helpMenu, logoutMenu);
        }
        return  menubar;
    }

    //this method creates the middle gridPane containing formName and two tableviews for room1 and room2
    protected GridPane createAndFillGridPane1(){
        Label lblPurchaseTickets = new Label("Purchase tickets");
        lblPurchaseTickets.setFont(new Font(25));
        Label lblRoom1 = new Label("Room 1");
        lblRoom1.setFont(new Font(15));
        Label lblRoom2 = new Label("Room 2");
        lblRoom2.setFont(new Font(15));

        //two tableviews for showing the data of room and room 2
        tableViewRoom1.setMinWidth(470);
        tableViewRoom2.setMinWidth(470);

        //calling a method to show the data of each room
        showRoomData(tableViewRoom1);
        showRoomData(tableViewRoom2);

        //creating the first gridPane to add the tableview,label
        GridPane.setConstraints(lblPurchaseTickets, 0, 1);
        GridPane.setConstraints(lblRoom1,0,2);
        GridPane.setConstraints(lblRoom2,2,2);
        GridPane.setConstraints(tableViewRoom1,0,3);
        GridPane.setConstraints(tableViewRoom2,2,3);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.getChildren().addAll(lblPurchaseTickets, tableViewRoom1, tableViewRoom2,lblRoom2,lblRoom1);

        return gridPane;
    }

    //this method prints the data of each room
    //method created to minimize code duplication
    private void showRoomData(TableView<Room> room) {
        TableColumn<Room, String> movieTitle = new TableColumn<>("Title");
        TableColumn<Room, Integer> NoOfSeats = new TableColumn<>("Seats");
        TableColumn<Room, String> startTime = new TableColumn<>("Start");
        TableColumn<Room, String> endTime = new TableColumn<>("End");
        TableColumn<Room, Double> price = new TableColumn<>("Price");


        movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        NoOfSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        startTime.setCellValueFactory(new PropertyValueFactory<>("startTimeMovie"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTimeMovie"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        room.getColumns().addAll(movieTitle, startTime, endTime, NoOfSeats, price);
    }
}
