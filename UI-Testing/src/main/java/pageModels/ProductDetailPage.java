package pageModels;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductDetailPage {

    private final SelenideElement backToProductsButton = $("#back-to-products");
    private final SelenideElement shoppingCartBadge = $(".shopping_cart_badge");
    private final SelenideElement addItemToCartButton = $("#add-to-cart");
    private final SelenideElement removeFromCartButton = $("#remove");
    private final SelenideElement productName = $("[data-test='inventory-item-name']");

    public void navigateBackToProducts() {
        if (backToProductsButtonExist()) {
            backToProductsButton.click();
        }
    }

    public boolean backToProductsButtonExist() {
        return backToProductsButton.exists();
    }

    public void addItemToCart() {
        addItemToCartButton.click();
    }

    public int getCartItemCount() {
        if (shoppingCartBadge.exists()) {
            return Integer.parseInt(shoppingCartBadge.getText());
        } else {
            return 0;
        }
    }

    public String getProductName() {
        return productName.getText();
    }

    public void removeItemFromCart() {
        if (removeFromCartButton.exists()) {
            removeFromCartButton.click();
        }
    }

    public void navigateToShoppingCartPage() {
        shoppingCartBadge.click();
    }
}
