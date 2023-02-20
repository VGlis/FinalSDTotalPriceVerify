package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.math.RoundingMode;
import java.math.BigDecimal;


import java.util.List;

public class CartPage {

    public WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }



    public WebElement getCheckout() {
        return driver.findElement(By.id("checkout"));
    }

    public void clickCheckout() {
        getCheckout().click();
    }

    public WebElement inputFirstName() {
        return driver.findElement(By.id("first-name"));
    }

    public WebElement inputLastName() {
        return driver.findElement(By.id("last-name"));
    }

    public WebElement inputZip() {
        return driver.findElement(By.id("postal-code"));
    }

    public void setFirstName(String FirstName) {
        inputFirstName().sendKeys(FirstName);
    }

    public void setLastName(String LastName) {
        inputLastName().sendKeys(LastName);
    }

    public void setZip(String Zip) {
        inputZip().sendKeys(Zip);
    }

    public WebElement getContinue() {
        return driver.findElement(By.id("continue"));
    }

    public void clickContinue() {
        getContinue().click();
    }

    public WebElement getTotal() {
        return driver.findElement(By.className("summary_total_label"));
    }

    public Double getTotalCount() {
        WebElement total = getTotal();
        return Double.parseDouble(total.getText().substring(8));
    }

    public Double sumTotalCount(){
        WebElement container = driver.findElement(By.className("cart_list"));
        WebElement tax = driver.findElement(By.className("summary_tax_label"));
        Double taxDouble = Double.parseDouble(tax.getText().substring(6));
        List<WebElement> listInventoryItems = container.findElements(By.className("item_pricebar"));
        double sum = 0;
        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemPriceFirst = listInventoryItems.get(i).findElement(By.className("inventory_item_price"));
            String itemPriceFirstText = itemPriceFirst.getText();
            Double itemPriceFirstNumber = Double.parseDouble(itemPriceFirstText.substring(1));
            sum += itemPriceFirstNumber ++;


        }
       Double sum_more_decimal = sum + taxDouble;
       BigDecimal bd = new BigDecimal(sum_more_decimal).setScale(2, RoundingMode.HALF_UP);
       double sum_2decimal = bd.doubleValue();
       return sum_2decimal;
    }

    public void printNamesProductInCart(){
        WebElement container = driver.findElement(By.className("cart_list"));

        List<WebElement> listInventoryItems = container.findElements(By.className("cart_item"));

        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemPriceFirst = listInventoryItems.get(i).findElement(By.className("inventory_item_name"));
            String itemPriceFirstText = itemPriceFirst.getText();
            System.out.println(itemPriceFirstText);
        }

    }









    public void close() {
        driver.close();
        driver.quit();
    }

}
