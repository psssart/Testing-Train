package pageModels;

import com.codeborne.selenide.Selenide;
import enums.UserName;
import enums.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPageTests extends TestsConfig{
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        loginPage.logIn(UserName.STANDARD_USER);
    }


    @Test
    public void givenProductsPage_whenAddingOneItemToCart_thenItemCountIncreasesByOne() {
        int initialItemCount = productsPage.getCartItemCount();

        productsPage.addItemToCart("Sauce Labs Bike Light");

        int updatedItemCount = productsPage.getCartItemCount();
        assertThat(updatedItemCount).isEqualTo(initialItemCount + 1);
    }

    @Test
    public void givenProductsPage_whenAddingMoreThanOneItemToCart_thenItemCountIncreasesMoreThanOne() {
        int initialItemCount = productsPage.getCartItemCount();

        int updatedItemCount = 0;
        int i = 0;
        for (Item item : Item.values()) {
            i++;
            productsPage.addItemToCart(item.getItemName());

            updatedItemCount = productsPage.getCartItemCount();
            assertThat(updatedItemCount).isEqualTo(initialItemCount + i);
        }
    }

    @Test
    public void givenProductsPage_whenSortingByNameAtoZ_thenItemsShouldBeSortedAtoZ() {
        productsPage.selectSortingAtoZ();
        ArrayList<String> productNames = productsPage.getProductNames();

        List<String> productNamesAtoZ = new ArrayList<>(productNames);
        Collections.sort(productNamesAtoZ);

        assertThat(productNamesAtoZ).isEqualTo(productNames);
    }

    @Test
    public void givenProductsPage_whenSortingByPriceAscending_thenItemsShouldBeSortedFromLowPriceToHighPrice() {
        productsPage.selectSortingPriceAscending();
        ArrayList<Float> productNames = productsPage.getProductPricesInTheirOrder();

        List<Float> productNamesByPrice = new ArrayList<>(productNames);
        Collections.sort(productNamesByPrice);

        assertThat(productNamesByPrice).isEqualTo(productNames);
    }
}
