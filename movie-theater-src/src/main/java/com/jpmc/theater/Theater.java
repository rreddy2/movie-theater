package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, false);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, false);
        schedule = List.of(
            new Showing( turningRed, 1, getCurrDayDateTime(9,0) ),
            new Showing( spiderMan, 2, getCurrDayDateTime(11,0) ),
            new Showing( theBatMan, 3, getCurrDayDateTime(12, 50) ),
            new Showing( turningRed, 4, getCurrDayDateTime(14, 30) ),
            new Showing( spiderMan, 5, getCurrDayDateTime(16, 10) ),
            new Showing( theBatMan, 6, getCurrDayDateTime(17, 50) ),
            new Showing( turningRed, 7, getCurrDayDateTime(19, 30) ),
            new Showing( spiderMan, 8, getCurrDayDateTime(21, 10) ),
            new Showing( theBatMan, 9, getCurrDayDateTime(23, 0) )
        );
    }

    private LocalDateTime getCurrDayDateTime(int hour, int minute){
        return LocalDateTime.of(provider.currentDate(), LocalTime.of(hour, minute));
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach( s -> System.out.println( s.toString() ) );
        System.out.println("===================================================");
        // print in JSON
        System.out.println("===================================================");
        schedule.forEach( s -> System.out.println( s.toJSONString() ) );
        System.out.println("===================================================");

    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }
}
