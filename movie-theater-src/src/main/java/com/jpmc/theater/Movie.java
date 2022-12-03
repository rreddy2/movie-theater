package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Movie {
    private static final double FIRST_SHOW_DISCOUNT = 3.00;
    private static final double SECOND_SHOW_DISCOUNT = 2.00;
    private static final double SEVENTH_DISCOUNT = 1.00;
    private static final double SPECIAL_MOVIE_DISCOUNT = 0.2;
    private static final double AFTERNOON_DISCOUNT = 0.25;
    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private boolean specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, boolean specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        int showSequence = showing.getSequenceOfTheDay();
        double discount = 0;
        if (specialCode) {
            discount = SPECIAL_MOVIE_DISCOUNT * ticketPrice;  // 20% discount for special movie
        }

        // check sequence discounts
        if (showSequence == 1 && discount < FIRST_SHOW_DISCOUNT) {
            discount = FIRST_SHOW_DISCOUNT; // $3 discount for 1st show
        } else if (showSequence == 2 && discount < SECOND_SHOW_DISCOUNT) {
            discount = SECOND_SHOW_DISCOUNT; // $2 discount for 2nd show
        }

        // check time discounts
        LocalDateTime startTime = showing.getStartTime();
        if(startTime != null) {
            if (startTime.getDayOfMonth() == 7 && discount < SEVENTH_DISCOUNT) {
                discount = SEVENTH_DISCOUNT;
            }
            int startHour = startTime.getHour();
            if (startHour >= 11 && startHour <= 15 && discount < (ticketPrice * AFTERNOON_DISCOUNT) ) {
                discount = ticketPrice * AFTERNOON_DISCOUNT;
            }
        }

        return ticketPrice - discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }
}