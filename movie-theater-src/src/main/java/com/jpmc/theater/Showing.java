package com.jpmc.theater;

import org.json.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    private double calculateFee(int audienceCount) {
        return movie.calculateTicketPrice(this) * audienceCount;
    }

    public String toString() {
        return sequenceOfTheDay + ": " + showStartTime + " " + movie.getTitle() + " " + humanReadableFormat(movie.getRunningTime()) + " $" + String.format("%.2f", movie.calculateTicketPrice(this));
    }

    public String toJSONString() {
        JSONObject object = new JSONObject();
        object.put("sequenceOfTheDay", sequenceOfTheDay);
        object.put("showStartTime", showStartTime);
        object.put("title", movie.getTitle());
        object.put("duration", humanReadableFormat(movie.getRunningTime()));
        object.put("ticketPrice", " $" + String.format("%.2f", movie.calculateTicketPrice(this)));
        return object.toString();
    }

    public String humanReadableFormat(Duration duration) {
        if (duration.toHours() > 0) {
            return String.format("(%s %s)", handlePlural(duration.toHours(), "hour"),
                    handlePlural(duration.toMinutes() % 60, "minute"));
        }
        return String.format("(%s)", handlePlural(duration.toMinutes() % 60, "minute"));
    }

    private String handlePlural(long value, String s) {
        if (value < 2) {
            return value + " " + s;
        }
        return value + " " + s + "s";
    }
}
