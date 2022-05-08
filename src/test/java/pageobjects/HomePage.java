package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage {
    private final String BASE_URL = "https://dodopizza.by/";
    private String patternCityLocator = "//a[text()='%s']";

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public HomePageForYourCity chooseCity(String city) {
        driver.findElement(By.xpath(String.format(patternCityLocator, city))).click();
        return new HomePageForYourCity(driver);

    }


}
