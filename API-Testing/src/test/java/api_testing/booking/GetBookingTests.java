package api_testing.booking;

import api_testing.TestsBase;
import api_testing.dto.Booking;
import api_testing.dto.BookingResponse;
import api_testing.generator.BookingGenerator;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class GetBookingTests extends TestsBase {

    @Test
    public void whenGetBookings_thenShouldReturnMoreThanOneBooking() {
        Response getBookingsResponse = bookingApi.getAllBookings();

        List<Integer> bookingIds = getBookingsResponse.jsonPath().getList("bookingid");

        assertThat(bookingIds)
                .isNotEmpty()
                .hasSizeGreaterThan(1);
    }

    @Test
    public void whenGetBookings_thenShouldNotContainBookingObject() {
        var bookingResponses = bookingApi.getAllBookings()
                .jsonPath()
                .getList("", BookingResponse.class);

        for (BookingResponse bookingResp : bookingResponses) {
            assertThat(bookingResp.getBooking()).isNull();
        }
    }
    @Test
    public void whenGetBookingById_thenShouldReturnBooking() {
        Integer bookingid = bookingApi.createBooking(BookingGenerator.getFullPayload())
                .as(BookingResponse.class)
                .getBookingid();

        Booking booking = bookingApi.getBooking(bookingid)
                .as(Booking.class);

        assertThat(booking.getFirstname()).isNotNull();
        assertThat(booking.getLastname()).isNotNull();
        assertThat(booking.getTotalprice()).isNotNull();
        assertThat(booking.getBookingdates()).isNotNull();
        assertThat(booking.isDepositpaid()).isNotNull();
    }
}
