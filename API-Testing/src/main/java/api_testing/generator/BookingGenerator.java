package api_testing.generator;

import api_testing.dto.Booking;
import api_testing.dto.BookingDates;
import com.github.javafaker.Faker;

import static java.util.concurrent.TimeUnit.DAYS;

public class BookingGenerator {

    private static final Faker FAKER = new Faker();

    public static Booking getFullPayload() {
        Booking booking = getBookingWithFirstAndLastNames();

        booking.setTotalprice(FAKER.number().numberBetween(100, 1000));
        booking.setDepositpaid(true);
        booking.setAdditionalneeds(FAKER.harryPotter().spell());
        booking.setBookingdates(generateBookingDates());

        return booking;
    }

    public static Booking getBookingWithFirstAndLastNames() {
        Booking booking = new Booking();

        booking.setFirstname(FAKER.name().firstName());
        booking.setLastname(FAKER.name().lastName());

        return booking;
    }

    private static BookingDates generateBookingDates() {
        BookingDates bookingDates = new BookingDates();

        bookingDates.setCheckin(FAKER.date().future(1, DAYS));
        bookingDates.setCheckout(FAKER.date().future(10, DAYS));

        return bookingDates;
    }
}
