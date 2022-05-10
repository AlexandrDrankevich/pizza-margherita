package test;

import org.junit.jupiter.api.Test;
import pageobjects.CartPage;
import pageobjects.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DodopizzaTest extends AbstractTest {
    private final String cityName = "Минск";
    private final String pizzaName = "Маргарита";
    private final String drinkName = "Pepsi";

    @Test
    public void testAddProductToCart() {
        CartPage cartPage = new HomePage(driver)
                .openPage().chooseCity(cityName)
                .chooseProductAddToCart(pizzaName)
                .chooseProductAddToCart(drinkName)
                .clickButtonCart();
        assertTrue(cartPage.isProductInCart(pizzaName));
        assertTrue(cartPage.isProductInCart(drinkName));
    }
}
