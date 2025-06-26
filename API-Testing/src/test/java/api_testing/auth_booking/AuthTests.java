package api_testing.auth_booking;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthTests extends AuthTestsBase {

    @Test
    public void postAuthenticationWithCorrectCredentialsReturns200() {
        Response authResponse = authApi
                .postAuthenticationWithCustomCredentials(validCredentials);

        int authResponseStatus = authResponse.getStatusCode();

        assertThat(authResponseStatus).isEqualTo(200);
    }

    @Test
    public void postAuthenticationWithIncorrectCredentialsReturnsBadCredentials() {
        Response authResponse = authApi
                .postAuthenticationWithCustomCredentials(invalidCredentials);

        String authResponseMessage = authResponse.getBody().jsonPath().getString("reason");

        assertThat(authResponseMessage).isEqualTo("Bad credentials");
    }

    @Test
    public void postAuthenticationWithCorrectCredentialsReturnsToken() {
        Response authResponse = authApi
                .postAuthenticationWithCustomCredentials(validCredentials);

        String token = authResponse.getBody().jsonPath().getString("token");

        assertThat(token).isNotEmpty();
    }

    @Test
    public void postAuthenticationWithIncorrectCredentialsDoesNotReturnToken() {
        Response authResponse = authApi
                .postAuthenticationWithCustomCredentials(invalidCredentials);

        String token = authResponse.getBody().jsonPath().getString("token");

        assertThat(token).isNull();
    }
}
