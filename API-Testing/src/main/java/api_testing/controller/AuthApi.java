package api_testing.controller;

import api_testing.dto.AuthCredentials;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi {
    public static final String API_URL = BASE_API_URL + "/auth";
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password123";

    public String getToken() {
        AuthCredentials validCredentials = new AuthCredentials();
        validCredentials.setUsername(VALID_USERNAME);
        validCredentials.setPassword(VALID_PASSWORD);

        Response authResponse = postAuthenticationWithCustomCredentials(validCredentials);

        return authResponse.getBody().jsonPath().getString("token");
    }

    public Response postAuthenticationWithCustomCredentials(AuthCredentials authCredentials) {

        return given()
                .header("Content-Type", ContentType.JSON.toString())
                .body(authCredentials)
                .and()
                .when()
                .post(API_URL)
                .then()
                .and()
                .extract().response();
    }
}
