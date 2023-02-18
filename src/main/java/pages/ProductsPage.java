package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public void printProductPrice() {
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemPrice = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            System.out.println(itemPrice.getText());
        }
    }

    public void printProductName() {
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemName = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            System.out.println(itemName.getText());
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

    public void sortByValue (String sortValue) {
        WebElement sortContainer = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));

        sortContainer.click();

        List<WebElement> options = sortContainer.findElements(By.xpath(".//option"));

        for(int i = 0; i < options.size(); i++) {
            String optionText = options.get(i).getText();
            if(optionText.equals(sortValue)) {
                options.get(i).click();
                break;
            }
        }
    }

    public void sortByName (String sortByName) {
        WebElement sortContainer = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));

        sortContainer.click();

        List<WebElement> options = sortContainer.findElements(By.xpath(".//option"));

        for(int i = 0; i < options.size(); i++) {
            String optionText = options.get(i).getText();
            if(optionText.equals(sortByName)) {
                options.get(i).click();
                break;
            }
        }
    }

    public boolean isProductSortFromHighToLowByPrice() {
        boolean toReturn = true;

        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < listInventoryItems.size() - 1; i++) {
            WebElement itemPriceFirst = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String itemPriceFirstText = itemPriceFirst.getText();
            Double itemPriceFirstNumber = Double.parseDouble(itemPriceFirstText.substring(1));
            int j = i + 1;
            WebElement itemPriceSecond = listInventoryItems.get(j).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            String itemPriceSecondText = itemPriceSecond.getText();
            Double itemPriceSecondNumber = Double.parseDouble(itemPriceSecondText.substring(1));
            if(itemPriceFirstNumber < itemPriceSecondNumber) {
                toReturn = false;
              //  break;
            }
        }

        return toReturn;
    }

  /*  public boolean isProductSortFromAZ() {
        boolean toReturn = false;

        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemName01 = listInventoryItems.get(0).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String itemNameText01 = itemName01.getText();
            System.out.println("First (1.) sort item: " + itemNameText01 + " = Sauce Labs Backpack");


            WebElement itemName06 = listInventoryItems.get(5).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String itemNameText06 = itemName06.getText();
            System.out.println("Last (6.) sort item: " + itemNameText06 + " = Test.allTheThings() T-Shirt (Red)");

            if(itemNameText01.equals("Sauce Labs Backpack") && itemNameText06.equals("Test.allTheThings() T-Shirt (Red)")) {
                toReturn = true;
                break;
            }
        }

        return toReturn;
    }*/

    public boolean isProductSortFromAZ() {
        boolean toReturn = false;
        WebElement container = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));


        String[] actual = new String[listInventoryItems.size()];
        String[] sorted = new String[listInventoryItems.size()];

        for (int i = 0; i < listInventoryItems.size(); i++)
        {
            WebElement itemNames = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            actual[i] = sorted[i] =  itemNames.getText();
        }

        //Sorting the array - ascending order - (A - Z)
        Arrays.sort(sorted);

        //Validating the existing with sorted array. shows message if both are same

        for(int i = 0; i < listInventoryItems.size(); i++)
        {
            int z = i + 1;
            if(actual[i].equals(sorted[i]))
            {
                System.out.println("|| Expected: "+sorted[i]+" || Actual: "+actual[i]+" || at row "+ z);
                toReturn = true;
            }
        }


        return toReturn;

    }

    public boolean isProductSortFromZA() {
        boolean toReturn = false;
        WebElement container = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));


        String[] actual = new String[listInventoryItems.size()];
        String[] sorted = new String[listInventoryItems.size()];

        for (int i = 0; i < listInventoryItems.size(); i++)
        {
            WebElement itemNames = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            actual[i] = sorted[i] =  itemNames.getText();
        }

        //Sorting the array reverseOrder() - descending order - (Z - A)
        Arrays.sort(sorted, Collections.reverseOrder());


        //Validating the existing with sorted array. shows  message if both are same

        for(int i = 0; i < listInventoryItems.size(); i++)
        {
            int z = i + 1;
            if(actual[i].equals(sorted[i]))
            {
                System.out.println("|| Expected: "+sorted[i]+" || Actual: "+actual[i]+" || at row "+ z);
                toReturn = true;
            }
        }


        return toReturn;

    }

    public WebElement getCart() {
        return driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    }

    public void openCart() {
        //driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        getCart().click();
    }

    public Integer getProductCount() {
        WebElement cart = getCart();
        return Integer.parseInt(cart.findElement(By.xpath(".//span")).getText());
    }

    public String getNameProductInProduct() {
        WebElement NameProductInProduct = driver.findElement(By.xpath("//*[@id='inventory_item_container']/div/div/div[2]/div[1]"));
        String NameProductInProductText = NameProductInProduct.getText();
        return NameProductInProductText;
    }

    public String getDescriptionProductInProduct() {
        WebElement DescriptionProductInProduct = driver.findElement(By.xpath("//*[@id='inventory_item_container']/div/div/div[2]/div[2]"));
        String DescriptionProductInProductText = DescriptionProductInProduct.getText();
        return DescriptionProductInProductText;
    }

    public Double getPriceProductInProduct() {
        WebElement PriceProductInProduct = driver.findElement(By.xpath("//*[@id='inventory_item_container']/div/div/div[2]/div[3]"));
        String PriceProductInProductText = PriceProductInProduct.getText();
        return Double.parseDouble(PriceProductInProductText.substring(1));
    }

    public WebElement getButtonCartFromSingleProduct() {
        return driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']"));
    }

    public void clickAddCartFromSingleProduct() {
        getButtonCartFromSingleProduct().click();

    }

    public List<String> isProductSortFromAZ_Array_ListString_Actual() {
        List<String> AZ_Array_actual  = new ArrayList<>();
        WebElement container = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for (int i = 0; i < listInventoryItems.size(); i++)
        {
            WebElement itemNames = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            AZ_Array_actual.add(itemNames.getText());
        }

        return AZ_Array_actual;
    }

    public List<String> isProductSortFromAZ_Array_ListString_sortedAZ() {
        List<String> AZ_Array_sorted  = new ArrayList<>();
        WebElement container = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for (int i = 0; i < listInventoryItems.size(); i++)
        {
            WebElement itemNames = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            AZ_Array_sorted.add(itemNames.getText());

        }
        Collections.sort(AZ_Array_sorted);
        return AZ_Array_sorted;
    }

    public List<String> isProductSortFromZA_Array_ListString_sortedZA() {
        List<String> ZA_Array_sorted  = new ArrayList<>();
        WebElement container = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for (int i = 0; i < listInventoryItems.size(); i++)
        {
            WebElement itemNames = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            ZA_Array_sorted.add(itemNames.getText());
        }

        Collections.sort(ZA_Array_sorted, Collections.reverseOrder());
        return ZA_Array_sorted;
    }

    public boolean isProductSortFromAZ_Array() {
        List<String> AZ_Array_actual  = new ArrayList<>();
        List<String> AZ_Array_sorted  = new ArrayList<>();
        AZ_Array_sorted.add("Sauce Labs Backpack");
        AZ_Array_sorted.add("Sauce Labs Bike Light");
        AZ_Array_sorted.add("Sauce Labs Bolt T-Shirt");
        AZ_Array_sorted.add("Sauce Labs Fleece Jacket");
        AZ_Array_sorted.add("Sauce Labs Onesie");
        AZ_Array_sorted.add("Test.allTheThings() T-Shirt (Red)");

        Collections.sort(AZ_Array_sorted);


        boolean toReturn = false;
        WebElement container = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));


        for (int i = 0; i < listInventoryItems.size(); i++)
        {
            WebElement itemNames = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            AZ_Array_actual.add(itemNames.getText());
        }

        System.out.println("Array_actual:    " + AZ_Array_actual);
        System.out.println("AZ_Array_sorted: " + AZ_Array_sorted);

        if (AZ_Array_actual.equals(AZ_Array_sorted)){
           toReturn = true;
        }
        return toReturn;
    }

    public boolean isProductSortFromZA_Array() {
        List<String> ZA_Array_actual  = new ArrayList<>();
        List<String> ZA_Array_sorted  = new ArrayList<>();

        ZA_Array_sorted.add("Test.allTheThings() T-Shirt (Red)");
        ZA_Array_sorted.add("Sauce Labs Onesie");
        ZA_Array_sorted.add("Sauce Labs Fleece Jacket");
        ZA_Array_sorted.add("Sauce Labs Bolt T-Shirt");
        ZA_Array_sorted.add("Sauce Labs Bike Light");
        ZA_Array_sorted.add("Sauce Labs Backpack");

        Collections.sort(ZA_Array_sorted, Collections.reverseOrder());


        boolean toReturn = false;
        WebElement container = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));


        for (int i = 0; i < listInventoryItems.size(); i++)
        {
            WebElement itemNames = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            ZA_Array_actual.add(itemNames.getText());
        }

        System.out.println("Array_actual:    " + ZA_Array_actual);
        System.out.println("ZA_Array_sorted: " + ZA_Array_sorted);

        if (ZA_Array_actual.equals(ZA_Array_sorted)){
            toReturn = true;
        }
        return toReturn;
    }

  /*  public boolean isProductSortFromAToZ() {
        boolean toReturn = false;
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for (int i = 0; i < listInventoryItems.size() - 1; i++) {
            WebElement itemNameCurrent = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            WebElement itemNameNext = listInventoryItems.get(i + 1).findElement(By.xpath(".//div[@class='inventory_item_name']"));

            if (itemNameNext.getText().compareTo(itemNameCurrent.getText()) > 0) {
                System.out.println("Array_actual:    " + itemNameNext.getText().compareTo(itemNameCurrent.getText()));
                toReturn = true;
            }


        }
        return toReturn;
    }
*/


}
