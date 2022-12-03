package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void testTotalFee() {
        double ticketPrice = 12.5;
        int audienceCount = 3;

        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), ticketPrice, true),
                1, LocalDateTime.now()
        );
        assertTrue(new Reservation(customer, showing, audienceCount).totalFee() == ticketPrice*audienceCount);
    }
}
