package pageModels;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {
    private final SelenideElement pageHeadingTitle = $(".title");
    private final SelenideElement firstNameField = $("#first-name");
    private final SelenideElement lastNameInput = $("#last-name");
    private final SelenideElement postalCodeInput = $("#postal-code");
    private final SelenideElement errorMessageField = $("[data-test='error']");
    private final SelenideElement cancelButton = $("#cancel");
    private final SelenideElement continueButton = $("#continue");
    private final SelenideElement finishButton = $("#finish");
    private final SelenideElement backToProducts = $("#back-to-products");

    public String getPageHeadingText() {
        return pageHeadingTitle.getText();
    }

    public void enterFirstName(String username) {
        firstNameField.setValue(username);
    }
    public void enterLastName(String username) {
        lastNameInput.setValue(username);
    }
    public void enterPostalCode(String username) {
        postalCodeInput.setValue(username);
    }


    public void clickContinue() {
        if (continueButton.exists()) {
            continueButton.click();
        }
    }

    public void clickCancel() {
        if (cancelButton.exists()) {
            cancelButton.click();
        }
    }

    public void clickFinish() {
        if (finishButton.exists()) {
            finishButton.click();
        }
    }

    public void clickBackToProducts() {
        if (backToProducts.exists()) {
            backToProducts.click();
        }
    }

    public String getErrorMessage() {
        return errorMessageField.getText();
    }

    public void fillWithCorrectValues() {
        enterFirstName("Pablo");
        enterLastName("Escobaro");
        enterPostalCode("12345");
    }

}
