package api_testing.auth_booking;

import api_testing.dto.Booking;
import api_testing.dto.BookingResponse;
import api_testing.generator.BookingGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PutBookingTests extends AuthTestsBase {

    @Test
    public void putCorrectBookingWithCorrectTokenShouldReturn200() {
        BookingResponse bookingResponse = createBooking();
        int bookingId = bookingResponse.getBookingid();
        Booking validBooking = bookingResponse.getBooking();
        String validToken = authApi.getToken();

        Response putBookingResponse = bookingApi
                .putBooking(bookingId, validBooking, validToken);
        int putBookingResponseStatus = putBookingResponse.getStatusCode();

        assertThat(putBookingResponseStatus).isEqualTo(200);
    }

    @Test
    public void putCorrectBookingWithIncorrectTokenShouldReturnError() {
        BookingResponse bookingResponse = createBooking();
        int bookingId = bookingResponse.getBookingid();
        Booking validBooking = bookingResponse.getBooking();
        String invalidToken = "invalidToken";

        Response putBookingResponse = bookingApi
                .putBooking(bookingId, validBooking, invalidToken);
        int putBookingResponseStatus = putBookingResponse.getStatusCode();

        assertThat(putBookingResponseStatus).isGreaterThan(399);
    }

    @Test
    public void putIncorrectBookingWithCorrectTokenShouldReturnError() {
        BookingResponse bookingResponse = createBooking();
        int bookingId = bookingResponse.getBookingid();
        Booking partialBooking = BookingGenerator.getBookingWithFirstAndLastNames();
        String validToken = authApi.getToken();

        Response putBookingResponse = bookingApi
                .putBooking(bookingId, partialBooking, validToken);
        int putBookingResponseStatus = putBookingResponse.getStatusCode();

        assertThat(putBookingResponseStatus).isGreaterThan(399);
    }

    @Test
    public void whenUpdateBooking_theShouldReturnBookingWithUpdatesValues() {
        BookingResponse bookingResponse = createBooking();
        int bookingId = bookingResponse.getBookingid();
        String validToken = authApi.getToken();

        Booking booking = bookingApi.getBooking(bookingId).as(Booking.class);

        booking.setFirstname("Pavel");
        booking.setLastname("Shubin");

        bookingApi.putBooking(bookingId, booking, validToken);

        Booking updatedBooking = bookingApi.getBooking(bookingId).as(Booking.class);

        assertThat(updatedBooking.getFirstname()).isEqualTo("Pavel");
        assertThat(updatedBooking.getLastname()).isEqualTo("Shubin");
    }
}
