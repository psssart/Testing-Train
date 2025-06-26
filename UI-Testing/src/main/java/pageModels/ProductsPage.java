package pageModels;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {

    private final SelenideElement productsHeader = $(".title");
    private final SelenideElement shoppingCartBadge = $(".shopping_cart_badge");

    private SelenideElement addItemToCartButton = null;
    private final SelenideElement sortingDropdown = $(".product_sort_container");

    ElementsCollection productNames = $$("[data-test='inventory-item-name']");
    ElementsCollection productsPrices = $$("[data-test='inventory-item-name']");

    public ArrayList<String> getProductNames() {
        ArrayList<String> productNamesList = new ArrayList<>();
        for (SelenideElement productName : productNames) {
            productNamesList.add(productName.getText());
        }
        return productNamesList;
    }

    public String getPageHeadingText() {
        return productsHeader.getText();
    }

    public void addItemToCart(String itemName) {
        String formattedItemName = formatItemId(itemName);
        ElementsCollection buttons = $$("[id^='add-to-cart']");
        addItemToCartButton = buttons.findBy(Condition.attributeMatching("id", ".*" + formattedItemName + ".*"));
        if (addItemToCartButton.exists()) {
            addItemToCartButton.click();
        }
    }

    private String formatItemId(String itemName) {
        return itemName.toLowerCase().replace(" ", "-");
    }

    public int getCartItemCount() {
        if (shoppingCartBadge.exists()) {
            return Integer.parseInt(shoppingCartBadge.getText());
        } else {
            return 0;
        }
    }

    public ArrayList<Float> getProductPricesInTheirOrder() {
        ArrayList<Float> prices = new ArrayList<>();

        for (SelenideElement productPriceEl : productsPrices) {
            String cleanedPriceStr = productPriceEl.toString().replaceAll("[^\\d.]", "");
            Float productPrice = Float.parseFloat(cleanedPriceStr);

            prices.add(productPrice);
        }
        return prices;
    }

    public void selectSortingAtoZ() {
        sortingDropdown.selectOptionByValue("az");
    }

    public void selectSortingPriceAscending() {
        sortingDropdown.selectOptionByValue("lohi");
    }

    public void navigateToProductDetailPage(String productName) {
        SelenideElement productNameLink = $$("[data-test='inventory-item-name']").findBy(text(productName));

        if (productNameLink.exists()) {
            productNameLink.click();
        }
    }

    public void navigateToShoppingCartPage() {
        shoppingCartBadge.click();
    }
}
