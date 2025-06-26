package pageModels;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    private final SelenideElement pageHeadingTitle = $(".title");
    private final SelenideElement shoppingCartBadge = $(".shopping_cart_badge");
    private final SelenideElement continueShoppingButton = $("#continue-shopping");
    private final SelenideElement checkoutButton = $("#checkout");

    ElementsCollection productNames = $$("[data-test='inventory-item-name']");

    public void clickContinueShopping() {
        if (continueShoppingButton.exists()) {
            continueShoppingButton.click();
        }
    }

    public String getPageHeadingText() {
        return pageHeadingTitle.getText();
    }

    public void clickCheckout() {
        if (checkoutButton.exists()) {
            checkoutButton.click();
        }
    }

    public int getCartItemCount() {
        if (shoppingCartBadge.exists()) {
            return Integer.parseInt(shoppingCartBadge.getText());
        } else {
            return 0;
        }
    }

    public void navigateToProductDetailPage(String productName) {
        SelenideElement productNameLink = $$("[data-test='inventory-item-name']").findBy(text(productName));

        if (productNameLink.exists()) {
            productNameLink.click();
        }
    }

    public List<String> getProductNames() {
        return productNames.texts();
    }

    public void removeProductByName(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        String removeButtonSelector = String.format("button[data-test='remove-%s']", formattedName);

        SelenideElement removeButton = $(removeButtonSelector);
        if (removeButton.exists()) {
            removeButton.click();
        }
    }

}
