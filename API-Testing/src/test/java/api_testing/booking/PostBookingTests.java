package api_testing.booking;

import api_testing.TestsBase;
import api_testing.dto.Booking;
import api_testing.dto.BookingResponse;
import api_testing.generator.BookingGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PostBookingTests extends TestsBase {

    @Test
    public void postBookingResponse_shouldContainId() {
        Integer bookingId = bookingApi.createBooking(BookingGenerator.getFullPayload())
                .as(BookingResponse.class)
                .getBookingid();

        assertThat(bookingId).isNotNull();
    }

    @Test
    public void postBookingWithPartialPayloadReturnsError() {
        Booking booking = BookingGenerator.getBookingWithFirstAndLastNames();

        Response createBookingResponse = bookingApi.createBooking(booking);
        int createBookingResponseStatus = createBookingResponse.getStatusCode();

        assertThat(createBookingResponseStatus).isGreaterThan(399);
    }

    @ParameterizedTest
    @ValueSource(strings = {"text/plain", "text/html"})
    public void postBookingWithWrongAcceptHeaderReturns418(String acceptHeader) {
        Booking booking = BookingGenerator.getFullPayload();

        Response createBookingResponse = bookingApi.createBooking(booking, acceptHeader);
        int createBookingResponseStatus = createBookingResponse.getStatusCode();

        assertThat(createBookingResponseStatus).isEqualTo(418);
    }

    @ParameterizedTest
    @ValueSource(strings = {"application/json", "application/xml"})
    public void postBookingWithCorrectAcceptHeaderReturns200(String acceptHeader) {
        Booking booking = BookingGenerator.getFullPayload();

        Response createBookingResponse = bookingApi.createBooking(booking, acceptHeader);
        int createBookingResponseStatus = createBookingResponse.getStatusCode();

        assertThat(createBookingResponseStatus).isEqualTo(200);
    }
}
