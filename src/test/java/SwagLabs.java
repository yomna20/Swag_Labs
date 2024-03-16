import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SwagLabs {

    ChromeDriver driver;

    void addItemToCart(String item) {
        String oldLocator = "//div[@class=\"inventory_item_name \"and text()=\"%s\"]/parent::a/parent::div/following-sibling::div/button[@class=\"btn btn_primary btn_small btn_inventory \"]";
        String newLocator = String.format(oldLocator, item);

        By newLocator1 = By.xpath(newLocator);
        WebElement new_Locator = driver.findElement(newLocator1);
        new_Locator.click();
    }

    void search(String item) {
        String oldLocator = "//div[@class=\"inventory_item_name\"and text()=\"%s\"]";
        String newLocator = String.format(oldLocator, item);
        Assert.assertEquals(driver.findElement(By.xpath(newLocator)).getText().contains(item), true);
    }

    @BeforeTest
    public void openUrl() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testDemo() {

        By userNameSelector = By.id("user-name");
        WebElement userName = driver.findElement(userNameSelector);
        userName.sendKeys("standard_user");

        By passwordSelector = By.id("password");
        WebElement password = driver.findElement(passwordSelector);
        password.sendKeys("secret_sauce");

        By loginSelector = By.id("login-button");
        WebElement button = driver.findElement(loginSelector);
        button.click();

        String item = "Sauce Labs Backpack";
        addItemToCart(item);

        WebElement cart = driver.findElement(By.className("shopping_cart_link"));
        cart.click();

        search(item);

    }
}