package pageModels;

import enums.Item;
import enums.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDetailPageTests extends TestsConfig{
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        productDetailPage = new ProductDetailPage();
        loginPage.logIn(UserName.STANDARD_USER);
    }

    @Test
    public void givenProductsPage_whenClickingItemLink_thenShouldSeeBackToProductsButton() {
        for (Item item : Item.values()) {
            productsPage.navigateToProductDetailPage(item.getItemName());

            assertTrue(productDetailPage.backToProductsButtonExist());
            productDetailPage.navigateBackToProducts();
        }
    }

    @Test
    public void givenProductsPage_whenClickingItemLink_thenShouldSeeItemName() {
        for (Item item : Item.values()) {
            productsPage.navigateToProductDetailPage(item.getItemName());

            assertThat(productDetailPage.getProductName()).isEqualTo(item.getItemName());
            productDetailPage.navigateBackToProducts();
        }
    }

    @Test
    public void givenProductsPage_whenAddItemToCartAndNavigateToProductDetailPage_thenShouldSeeSameCartSize() {
        for (Item item : Item.values()) {
            productsPage.addItemToCart(item.getItemName());
            int productsPageCartSize = productsPage.getCartItemCount();
            productsPage.navigateToProductDetailPage(item.getItemName());

            assertThat(productsPageCartSize).isEqualTo(productDetailPage.getCartItemCount());
            productDetailPage.navigateBackToProducts();
        }
    }

    @Test
    public void givenProductDetailPage_whenAddItemToCart_thenItemCountIncreasesByOne() {
        productsPage.navigateToProductDetailPage(Item.SAUCE_LABS_BACKPACK.getItemName());
        int initialItemCount = productDetailPage.getCartItemCount();

        productDetailPage.addItemToCart();
        assertThat(productDetailPage.getCartItemCount()).isEqualTo(initialItemCount + 1);
    }

    @Test
    public void givenProductDetailPage_whenRemovingItemToCart_thenItemCountDecreasesByOne() {
        productsPage.navigateToProductDetailPage(Item.SAUCE_LABS_BACKPACK.getItemName());
        int initialItemCount = productDetailPage.getCartItemCount();

        productDetailPage.addItemToCart();
        assertThat(productDetailPage.getCartItemCount()).isEqualTo(initialItemCount + 1);

        productDetailPage.removeItemFromCart();
        assertThat(productDetailPage.getCartItemCount()).isEqualTo(initialItemCount);
    }
}
