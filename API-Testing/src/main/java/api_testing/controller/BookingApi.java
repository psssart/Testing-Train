package api_testing.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import api_testing.dto.Booking;
import io.restassured.response.Response;

public class BookingApi extends BaseApi {
    public static final String API_URL = BASE_API_URL + "/booking";

    public Response getAllBookings() {
        return given()
                .when()
                .get(API_URL)
                .then()
                .extract().response();
    }

    public Response getBooking(Integer bookingId) {
        return given()
                .when()
                .get(API_URL + "/" + bookingId)
                .then()
                .extract().response();
    }

    public Response createBooking(Booking booking) {
        return given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(booking)
                .when()
                .post(API_URL)
                .then()
                .extract().response();
    }

    public Response createBooking(Booking booking, String acceptHeader) {
        return given()
                .contentType(JSON.toString())
                .accept(acceptHeader)
                .body(booking)
                .when()
                .post(API_URL)
                .then()
                .extract().response();
    }

    public Response deleteBooking(int bookingId, String token) {
        return given()
                .header("Cookie", "token=" + token)
                .when()
                .delete(API_URL + "/" + bookingId)
                .then()
                .extract().response();
    }

    public Response putBooking(int bookingId, Booking booking, String token) {
        return given()
                .header("Cookie", "token=" + token)
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(booking)
                .when()
                .put(API_URL + "/" + bookingId)
                .then()
                .extract().response();
    }
}
