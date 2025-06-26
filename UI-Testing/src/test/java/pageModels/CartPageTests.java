package pageModels;

import enums.Item;
import enums.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPageTests extends TestsConfig{
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        productDetailPage = new ProductDetailPage();
        cartPage = new CartPage();
        loginPage.logIn(UserName.STANDARD_USER);
    }

    @Test
    public void givenCartPage_whenClickingBackToProducts_thenTitleShouldBeProducts() {
        String expectedHeadingText = "Products";

        cartPage.clickContinueShopping();
        String actualHeadingText = productsPage.getPageHeadingText();

        assertThat(expectedHeadingText).isEqualTo(actualHeadingText);
    }

    @Test
    public void givenCartPageWithItemsInCart_thenShouldSeeCorrectItemsCount() {
        addItemsToCart();
        productsPage.navigateToShoppingCartPage();

        List<String> cartItemsNames = cartPage.getProductNames();

        assertThat(cartItemsNames.size()).isEqualTo(cartPage.getCartItemCount());
    }

    @Test
    public void givenCartPageWithItemsInCart_whenClickingOnItemTitle_thenShouldNavigateToCorrectProductDetailPage() {
        addItemsToCart();
        productsPage.navigateToShoppingCartPage();

        for (Item item : Item.values()) {
            cartPage.navigateToProductDetailPage(item.getItemName());

            assertThat(productDetailPage.getProductName()).isEqualTo(item.getItemName());
            productDetailPage.navigateToShoppingCartPage();
        }
    }

    @Test
    public void givenCartPageWithItemsInCart_whenRemovingItems_thenShouldSeeCorrectItemsCount() {
        addItemsToCart();
        productsPage.navigateToShoppingCartPage();

        int initialItemsCount = cartPage.getCartItemCount();
        for (Item item : Item.values()) {
            cartPage.removeProductByName(item.getItemName());

            assertThat(cartPage.getCartItemCount()).isEqualTo(--initialItemsCount);
        }
    }


    private void addItemsToCart() {
        for (Item item : Item.values()) {
            productsPage.addItemToCart(item.getItemName());
        }
    }
}
