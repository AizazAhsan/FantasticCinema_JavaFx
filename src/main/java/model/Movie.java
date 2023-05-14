package model;

import javafx.scene.control.Label;

public class Movie {
    private String title;
    private double price;
    private int duration;
    private Label lblAge;
    private int ageRestriction;

    public Movie() {
    }

    public Movie(String title, double price,int duration) {
        this.title = title;
        this.price = price;
        this.duration=duration;
    }

    public String getTitle() {
        return title;
    }

    public int getAgeRestriction() { return ageRestriction; }

    public void setAgeRestriction(int age) {
        if(age <= 16){
            lblAge = new Label("Customers have to be older than 16 to view this movie. Please check" +
                    "the age using the ID card.");
        }
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
