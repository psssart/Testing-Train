package api_testing.auth_booking;

import api_testing.dto.BookingResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteBookingTests extends AuthTestsBase{

    @Test
    public void whenDeleteBooking_withValidToken_thenShouldReturnStatus201() {
        BookingResponse createdBooking = createBooking();
        String validToken = authApi.getToken();

        int bookingId = createdBooking.getBookingid();
        Response deleteBookingResponse = bookingApi.deleteBooking(bookingId, validToken);

        assertThat(deleteBookingResponse.getStatusCode()).isEqualTo(201);
    }

    @Test
    public void whenDeleteBooking_withInvalidToken_thenShouldReturnError() {
        BookingResponse createdBooking = createBooking();
        String invalidToken = "invalidToken";

        int bookingId = createdBooking.getBookingid();
        Response deleteBookingResponse = bookingApi.deleteBooking(bookingId, invalidToken);

        assertThat(deleteBookingResponse.getStatusCode()).isGreaterThan(399);
    }

    @Test
    public void givenDeletedBookingId_whenGetBookingById_thenShouldReturnStatus404() {
        BookingResponse createdBooking = createBooking();
        String validToken = authApi.getToken();

        int bookingId = createdBooking.getBookingid();
        bookingApi.deleteBooking(bookingId, validToken);
        Response getBookingResponse = bookingApi.getBooking(bookingId);

        assertThat(getBookingResponse.getStatusCode()).isEqualTo(404);
    }

    @Test
    public void whenDeleteBooking_withNegativeBookingId_thenShouldReturnStatus404() {
        String validToken = authApi.getToken();

        Response deleteBookingResponse = bookingApi.deleteBooking(-11, validToken);

        assertThat(deleteBookingResponse.getStatusCode()).isEqualTo(405);
    }
}
