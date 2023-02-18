package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Scanner;

import java.util.List;

public class CartPage {

    public WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {

    }

    public boolean isProductInCart(String productName) {
        boolean toReturn = false;

        WebElement cartList = driver.findElement(By.xpath("//div[@class='cart_list']"));

        List<WebElement> listItems = cartList.findElements(By.xpath(".//div[@class='cart_item']"));

        for(int i = 0; i < listItems.size(); i++) {
            WebElement name = listItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            if(name.getText().equals(productName)) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
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
       return sum + taxDouble;
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

    public String getNameProductInCart() {
        WebElement NameProductInCart = driver.findElement(By.className("inventory_item_name"));
        String NameProductInCartText = NameProductInCart.getText();
        return NameProductInCartText;
    }

    public String getDescriptionProductInCart() {
        WebElement DescriptionProductInCart = driver.findElement(By.className("inventory_item_desc"));
        String DescriptionProductInCartText = DescriptionProductInCart.getText();
        return DescriptionProductInCartText;
    }

    public Double getPriceProductInCart() {
        WebElement PriceProductInCart = driver.findElement(By.className("inventory_item_price"));
        String PriceProductInCartText = PriceProductInCart.getText();
        return Double.parseDouble(PriceProductInCartText.substring(1));
    }

    public String finishCart() {
        WebElement DescriptionFinishCart = driver.findElement(By.className("complete-header"));
        String DescriptionFinishCartText = DescriptionFinishCart.getText();
        return DescriptionFinishCartText;
    }

    public WebElement getFinish() {
        return driver.findElement(By.id("finish"));
    }

    public void clickFinish() {
        getFinish().click();
    }





    public void close() {
        driver.close();
        driver.quit();
    }

}
