package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Room {

    private Movie movie;
    private String title;
    private double price;
    private int seats;
    private LocalDateTime startDateNTime;
    private LocalDateTime endDateNTime;
    private String startTimeMovie;
    private String endTimeMovie;

    public Room() {
    }

    public Room(Movie movie, int seats, LocalDateTime startTime, LocalDateTime endTime) {
        this.movie = movie;
        this.seats = seats;
        this.startDateNTime = startTime;
        this.endDateNTime = endTime;
        this.title = movie.getTitle();
        this.price = movie.getPrice();
        this.startTimeMovie = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.endTimeMovie = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }

    public String getStartTimeMovie() {
        return startTimeMovie;
    }

    public String getEndTimeMovie() {
        return endTimeMovie;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getTitle() {
        return title;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public LocalDateTime getStartTime() {
        return startDateNTime;
    }

    public LocalDateTime getEndTime() {
        return endDateNTime;
    }
}
