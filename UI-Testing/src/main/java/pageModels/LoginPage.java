package pageModels;

import com.codeborne.selenide.SelenideElement;
import enums.UserName;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameField = $("#user-name");
    private final SelenideElement passwordField = $("#password");
    private final SelenideElement loginButtonField = $("#login-button");
    private final SelenideElement errorMessageField =  $("[data-test='error']");

    public void enterUsername(String username){
        usernameField.setValue(username);
    }

    public void enterPassword(String password){
        passwordField.setValue(password);
    }

    public void clickLoginButton(){
        loginButtonField.click();
    }

    public void logIn(String userName){
        enterUsername(userName);
        enterPassword("secret_sauce");
        clickLoginButton();
    }

    public void logIn(String userName, String password){
        enterUsername(userName);
        enterPassword(password);
        clickLoginButton();
    }

    public void logIn(UserName user) {
        logIn(user.getUsername());
    }

    public void logIn(UserName user, String password) {
        logIn(user.getUsername(), password);
    }

    public String getErrorMessage(){
        return errorMessageField.exists() ? errorMessageField.getText() : "";
    }
}
