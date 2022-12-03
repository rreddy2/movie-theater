package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    LocalDateTime testTime = LocalDateTime.of(LocalDate.of(2000, 1,1), LocalTime.of(1,0));
    LocalDateTime seventhTestTime = LocalDateTime.of(LocalDate.of(2000, 1,7), LocalTime.of(1,0));
    LocalDateTime afternoonTestTime = LocalDateTime.of(LocalDate.of(2000, 1,7), LocalTime.of(12,0));


    @Test
    void testMovieWithSpecialDiscount() {
        // Test the Special discount 20%
        double ticketPrice = 12.5;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),ticketPrice, true);
        Showing showing = new Showing(spiderMan, 5, testTime);
        assertEquals(ticketPrice*0.8, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testMovieWithSequenceDiscount() {
        // Test the Sequence discount $3
        double ticketPrice = 12.5;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),ticketPrice, false);
        Showing showing = new Showing(spiderMan, 1, testTime);
        assertEquals(ticketPrice-3, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testMovieWithSecondSequenceDiscount() {
        // Test the Sequence discount $2
        double ticketPrice = 12.5;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),ticketPrice, false);
        Showing showing = new Showing(spiderMan, 2, testTime);
        assertEquals(ticketPrice-2, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testMovieWithSeventhDayDiscount() {
        // Test the Sequence discount $1
        double ticketPrice = 12.5;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),ticketPrice, false);
        Showing showing = new Showing(spiderMan, 3, seventhTestTime);
        assertEquals(ticketPrice-1, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testMovieWithAfternoonDiscount() {
        // Test the Sequence discount 25%
        double ticketPrice = 12.5;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),ticketPrice, false);
        Showing showing = new Showing(spiderMan, 3, afternoonTestTime);
        assertEquals(ticketPrice*0.75, spiderMan.calculateTicketPrice(showing));
    }
}
