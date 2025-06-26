package api_testing.booking;

import api_testing.TestsBase;
import api_testing.dto.BookingResponse;
import api_testing.generator.BookingGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingSmokeTests extends TestsBase {

    @Test
    public void givenAcceptType_whenGetAllBooking_thenShouldReturnHttpStatus200() {
        Response getBookingsResponse = bookingApi.getAllBookings();

        assertThat(getBookingsResponse.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void givenBookingRequest_whenCreateBookingIsCalled_thenReturnHttp200() {
        Response createBookingResponse = bookingApi.createBooking(BookingGenerator.getFullPayload());

        assertThat(createBookingResponse.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void getBookingById_shouldReturn200() {
        Integer bookingId = bookingApi.createBooking(BookingGenerator.getFullPayload())
                .as(BookingResponse.class)
                .getBookingid();

        assertThat(bookingApi.getBooking(bookingId).getStatusCode()).isEqualTo(200);
    }
}
