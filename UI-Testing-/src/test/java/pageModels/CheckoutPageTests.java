package pageModels;

import enums.Error;
import enums.Item;
import enums.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckoutPageTests extends TestsConfig{
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        productDetailPage = new ProductDetailPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        loginPage.logIn(UserName.STANDARD_USER);
        addItemsToCart();
        productsPage.navigateToShoppingCartPage();
        cartPage.clickCheckout();
    }

    @Test
    public void givenNoFirstName_whenClickingContinue_thenErrorShouldBeDisplayed() {
        checkoutPage.enterLastName("Escobaro");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();

        String errorMessage = checkoutPage.getErrorMessage();
        assertThat(errorMessage).isEqualTo(Error.FIRST_NAME_REQUIRED.getErrorMessage());
    }

    @Test
    public void givenNoLastName_whenClickingContinue_thenErrorShouldBeDisplayed() {
        checkoutPage.enterFirstName("Pablo");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();

        String errorMessage = checkoutPage.getErrorMessage();
        assertThat(errorMessage).isEqualTo(Error.LAST_NAME_REQUIRED.getErrorMessage());
    }

    @Test
    public void givenNoPostalCode_whenClickingContinue_thenErrorShouldBeDisplayed() {
        checkoutPage.enterFirstName("Pablo");
        checkoutPage.enterLastName("Escobaro");
        checkoutPage.clickContinue();

        String errorMessage = checkoutPage.getErrorMessage();
        assertThat(errorMessage).isEqualTo(Error.POSTAL_CODE_REQUIRED.getErrorMessage());
    }

    @Test
    public void givenCheckoutPage_whenClickingCancel_thenShouldNavigateToCart() {
        checkoutPage.clickCancel();

        String actualHeading = cartPage.getPageHeadingText();
        assertThat(actualHeading).isEqualTo("Your Cart");
    }


    @Test
    public void givenValidCheckoutInfo_whenClickingContinue_thenShouldNavigateToOverviewPage() {
        checkoutPage.fillWithCorrectValues();
        checkoutPage.clickContinue();

        String actualHeading = checkoutPage.getPageHeadingText();
        assertThat(actualHeading).isEqualTo("Checkout: Overview");
    }

    @Test
    public void givenCheckoutOverviewPage_whenClickingFinish_thenShouldNavigateToCompletePage() {
        checkoutPage.fillWithCorrectValues();
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        String actualHeading = checkoutPage.getPageHeadingText();
        assertThat(actualHeading).isEqualTo("Checkout: Complete!");
    }

    @Test
    public void givenCompletePage_whenClickingBackToProducts_thenShouldNavigateToProductsPage() {
        checkoutPage.fillWithCorrectValues();
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        checkoutPage.clickBackToProducts();

        String actualHeading = productsPage.getPageHeadingText();
        assertThat(actualHeading).isEqualTo("Products");
    }


    private void addItemsToCart() {
        for (Item item : Item.values()) {
            productsPage.addItemToCart(item.getItemName());
        }
    }
}
