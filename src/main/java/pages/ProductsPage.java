package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class ProductsPage {

    public WebDriver driver;

    public ProductsPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public boolean isOpen() {
        String url = driver.getCurrentUrl();

        if(url.equals("https://www.saucedemo.com/inventory.html")) {
            return true;
        }
        else {
            return false;
        }

    }

    public void close() {
        driver.close();
        driver.quit();
    }

    public void addProductToCartByName(String productName) {
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemName = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String itemNameText = itemName.getText();

            if(itemNameText.equals(productName)) {
                WebElement addButton = listInventoryItems.get(i).findElement(By.xpath(".//button"));
                addButton.click();
                break;
            }
        }
    }


    public Integer productCountInCart() {
        //WebElement cartNumber = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        //return Integer.parseInt(cartNumber.getText());

        List<WebElement> listCartBadge = driver.findElements(By.xpath("//span[@class='shopping_cart_badge']"));
        if(listCartBadge.size() == 0) {
            return 0;
        }
        else {
            return Integer.parseInt(listCartBadge.get(0).getText());
        }
    }


    public WebElement getCart() {
        return driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    }

    public void openCart() {
        //driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        getCart().click();
    }





}
