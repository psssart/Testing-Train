package api_testing.auth_booking;

import api_testing.TestsBase;
import api_testing.controller.AuthApi;
import api_testing.dto.AuthCredentials;
import api_testing.dto.Booking;
import api_testing.dto.BookingResponse;
import api_testing.generator.BookingGenerator;
import org.junit.jupiter.api.BeforeAll;

public abstract class AuthTestsBase extends TestsBase {

    protected static AuthApi authApi;
    protected static AuthCredentials validCredentials;
    protected static AuthCredentials invalidCredentials;

    @BeforeAll
    public static void setUp() {
        TestsBase.setUp();

        authApi = new AuthApi();
        validCredentials = new AuthCredentials();
        validCredentials.setUsername("admin");
        validCredentials.setPassword("password123");

        invalidCredentials = new AuthCredentials();
        invalidCredentials.setUsername("admin");
        invalidCredentials.setPassword("password321");
    }

    protected BookingResponse createBooking() {
        Booking booking = BookingGenerator.getFullPayload();
        return bookingApi.createBooking(booking).as(BookingResponse.class);
    }
}
