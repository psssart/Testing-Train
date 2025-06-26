package api_testing;

import api_testing.controller.BookingApi;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class TestsBase {

    protected static BookingApi bookingApi;

    // A simple health check endpoint to confirm whether the API is up and running.
    @BeforeAll
    public static void apiIsUpCheck() {
        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/ping")
                .then()
                .statusCode(201);
    }

    @BeforeAll
    public static void enableLoggingOfRequestAndResponse() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeAll
    public static void setUp() {
        bookingApi = new BookingApi();
    }
}
