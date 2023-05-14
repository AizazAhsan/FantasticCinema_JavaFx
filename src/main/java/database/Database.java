package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    private ArrayList<User> users;
    private ObservableList<Room> room1;
    private ObservableList<Room> room2;
    private ObservableList<Movie> movies;

    public Database() {
        room1 = FXCollections.observableArrayList();
        room2 = FXCollections.observableArrayList();
        movies = FXCollections.observableArrayList();

        createUsersAndMovies();
    }

    public void createUsersAndMovies(){
        users = new ArrayList<>(){
            {
                add(new User("user","normalpassword", UserRole.User));
                add(new User("admin","adminpassword",UserRole.Admin));
            }
        };

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String startTime1 = "23-10-2021 12:00";
    LocalDateTime startTime11 = LocalDateTime.parse(startTime1, formatter);
    String endTime1 = "23-10-2021 14:05";
    LocalDateTime endTime11 = LocalDateTime.parse(endTime1, formatter);
    String startTime2 = "23-10-2021 15:00";
    LocalDateTime startTime22 = LocalDateTime.parse(startTime2, formatter);
    String endTime2 = "23-10-2021 16:32";
    LocalDateTime endTime22 = LocalDateTime.parse(endTime2, formatter);

    Movie movie1 = new Movie ("No time to lie",12,125);
    Movie movie2 = new Movie("The Addams Family 19",9,92);
    movies.add(movie1);
    movies.add(movie2);

    room1.addAll(Arrays.asList(new Room(movie1,200,startTime11,endTime11),
            new Room(movie2,200,startTime22,endTime22)));
    room2.addAll(Arrays.asList(new Room(movie2,100,startTime22,endTime22),
            new Room(movie1,100,startTime11,endTime11)));
    }

    public void addRoom1(Room r){
        room1.addAll(r);
    }

    public void addRoom2(Room r){
        room2.add(r);
    }

    public void addMovie(Movie m){
        movies.add(m);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }

    public ObservableList<Room> getRoom1() {
        return room1;
    }

    public ObservableList<Room> getRoom2() {
        return room2;
    }
}