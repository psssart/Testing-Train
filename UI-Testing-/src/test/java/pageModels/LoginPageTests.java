package pageModels;

import enums.Error;
import enums.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTests extends TestsConfig {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
    }

    @Test
    public void givenCorrectCredentials_whenLogIn_thenShouldNotSeeMessage(){
        loginPage.logIn(UserName.STANDARD_USER);
        assertThat(loginPage.getErrorMessage()).isEqualTo("");
    }

    @Test
    public void givenEmptyCredentials_whenLogin_thenShouldDisplayUsernameIsRequired() {
        loginPage.logIn(UserName.EMPTY);
        assertThat(loginPage.getErrorMessage()).isEqualTo(Error.USERNAME_REQUIRED.getErrorMessage());
    }

    @Test
    public void givenEmptyPassword_whenLogin_thenShouldDisplayPasswordIsRequired() {
        loginPage.logIn(UserName.STANDARD_USER, "");

        assertThat(loginPage.getErrorMessage()).isEqualTo(Error.PASSWORD_REQUIRED.getErrorMessage());
    }

    @Test
    public void givenInCorrectCredentials_whenLogIn_thenShouldSeeNotMatchMessage(){
        loginPage.logIn(UserName.INCORRECT_USER);
        assertThat(loginPage.getErrorMessage()).isEqualTo(Error.NOT_MATH.getErrorMessage());
    }

    @Test
    public void givenLockedOutUser_whenLogIn_thenShouldSeeLockedOutMessage(){
        loginPage.logIn(UserName.LOCKED_OUT_USER);
        assertThat(loginPage.getErrorMessage()).isEqualTo(Error.USER_LOCKED.getErrorMessage());
    }

    @Test
    public void givenCorrectCredentials_whenLogin_thenShouldSeeProductsTitle() {
        loginPage.logIn(UserName.STANDARD_USER);

        assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }


    @Test
    public void givenProblemUserCredentials_whenLogIn_ShouldSeeProductsTitle() {
        loginPage.logIn(UserName.PROBLEM_USER);
        assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }

    @Test
    public void givenPerformanceGlitchUserCredentials_whenLogIn_ShouldSeeProductsTitleAfterSomeTime() {
        loginPage.logIn(UserName.PERFORMANCE_GLITCH_USER);
        assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }

    @Test
    public void givenErrorUserCredentials_whenLogIn_ShouldSeeProductsTitle() {
        loginPage.logIn(UserName.ERROR_USER);
        assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }

    @Test
    public void givenVisualUserCredentials_whenLogIn_ShouldSeeProductsTitle() {
        loginPage.logIn(UserName.VISUAL_USER);
        assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }
}
