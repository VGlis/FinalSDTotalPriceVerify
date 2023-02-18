package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import provider.ProductsProvider;

import java.util.ArrayList;
import java.util.List;

public class ProductsTests {

    public static List<String> allItems() {
        List<String> toReturn  = new ArrayList<>();

        toReturn.add("Sauce Labs Bike Light");
        toReturn.add("Sauce Labs Onesie");
        toReturn.add("Sauce Labs Fleece Jacket");
        toReturn.add("Sauce Labs Backpack");
        toReturn.add("Sauce Labs Bolt T-Shirt");
        toReturn.add("Test.allTheThings() T-Shirt (Red)");

        return toReturn;
    }

    @Test
    public void verifySortProductByNameAZ() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortByName( "Name (A to Z)");

        boolean isSortedByNameAZ = productsPage.isProductSortFromAZ();

        if(isSortedByNameAZ) {
            System.out.println("|| Sorting is as expected ||");
        }
        else {
            System.out.println("|| Sorting is not as expected ||");
        }

        Assert.assertEquals(isSortedByNameAZ, true, "Products are not sorted as expected, from Name (A to Z)");

        productsPage.close();
    }

    @Test
    public void verifySortProductByNameZA() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortByName( "Name (Z to A)");

        boolean isProductSortFromZA = productsPage.isProductSortFromZA();

        if(isProductSortFromZA) {
            System.out.println("|| Sorting is as expected ||");
        }
        else {
            System.out.println("|| Sorting is not as expected ||");
        }

        Assert.assertEquals(isProductSortFromZA, true, "Products are not sorted as expected, from Name (Z to A)");

        productsPage.close();
    }


    @Test
    public void verifyTotalPriceProduct() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);

        System.out.println("Number of Items in Cart before add: " + productsPage.productCountInCart());
        Assert.assertEquals(productsPage.productCountInCart(), 0, "Product count is not equals");

        productsPage.addProductToCartByName("Sauce Labs Backpack");
        productsPage.addProductToCartByName("Sauce Labs Bike Light");
        productsPage.addProductToCartByName("Sauce Labs Fleece Jacket");
        productsPage.addProductToCartByName("Sauce Labs Onesie");
        productsPage.addProductToCartByName("Sauce Labs Bolt T-Shirt");
        productsPage.addProductToCartByName("Test.allTheThings() T-Shirt (Red)");

        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        cartPage.setFirstName("Vladan");
        cartPage.setLastName("Glisovic");
        cartPage.setZip("11000");
        cartPage.clickContinue();

        System.out.println("Number of Items in Cart after add: " + productsPage.productCountInCart());
        System.out.println("List of items in Cart: ");
        cartPage.printNamesProductInCart();
        System.out.println("**************************************************************************");
        System.out.println("Total sum count in CartPage by APP: $" + cartPage.getTotalCount());
        System.out.println("Total sum count in CartPage by QA:  $" + cartPage.sumTotalCount());

        Assert.assertEquals(cartPage.getTotalCount(), cartPage.sumTotalCount(), "Product count is not equals");

        cartPage.close();
    }
    @Test
    public void verifyProductDescription() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        loginPage.clickProductLinkTextSauceLabsBackpack();

        ProductsPage productsPage = new ProductsPage(driver);

        System.out.println("Number of Items in Cart before add: " + productsPage.productCountInCart());
        Assert.assertEquals(productsPage.productCountInCart(), 0, "Product count is not equals");

        String NameProductInProductText = productsPage.getNameProductInProduct();
        String DescriptionProductInProductText = productsPage.getDescriptionProductInProduct();
        Double getPriceProductInProduct = productsPage.getPriceProductInProduct();

        System.out.println("Name of Product in Single Item:         " + NameProductInProductText);
        System.out.println("Description of Product in Single Item:  " + DescriptionProductInProductText);
        System.out.println("Price of Product in Single Item:        $" + getPriceProductInProduct);

        productsPage.clickAddCartFromSingleProduct();
        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);

        System.out.println("***************************************************************************************");

        String NameProductInCartText = cartPage.getNameProductInCart();
        String DescriptionProductInCartText = cartPage.getDescriptionProductInCart();
        Double getPriceProductInCart = cartPage.getPriceProductInCart();

        System.out.println("Name of single Product in Cart Page:         " + NameProductInProductText);
        System.out.println("Description of single Product in Cart Page:  " + DescriptionProductInProductText);
        System.out.println("Price of single Product in Cart Page:        $" + getPriceProductInCart);


        Assert.assertEquals(NameProductInCartText, NameProductInProductText, "Product Name is not equals");
        Assert.assertEquals(DescriptionProductInProductText, DescriptionProductInCartText, "Product Description is not equals");
        Assert.assertEquals(getPriceProductInProduct, getPriceProductInCart, "Product Price is not equals");


        cartPage.close();
    }

    @Test
    public void verifySortProductByNameAZ_Array() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortByName( "Name (A to Z)");

        boolean isSortedByNameAZ = productsPage.isProductSortFromAZ_Array();

        if(isSortedByNameAZ) {
            System.out.println("|| Sorting is as expected ||");
        }
        else {
            System.out.println("|| Sorting is not as expected ||");
        }

        Assert.assertEquals(isSortedByNameAZ, true, "Products are not sorted as expected, from Name (A to Z)");

        productsPage.close();
    }

    @Test
    public void verifySortProductByNameZA_Array() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortByName( "Name (Z to A)");

        boolean isSortedByNameAZ = productsPage.isProductSortFromZA_Array();

        if(isSortedByNameAZ) {
            System.out.println("|| Sorting is as expected ||");
        }
        else {
            System.out.println("|| Sorting is not as expected ||");
        }

        Assert.assertEquals(isSortedByNameAZ, true, "Products are not sorted as expected, from Name (A to Z)");

        productsPage.close();
    }
    /*@Test
    public void verifySortProductByNameAZcompateTo() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.sortByName( "Name (A to Z)");

        boolean isSortedByNameAZ = productsPage.isProductSortFromAToZ();

        if(isSortedByNameAZ) {
            System.out.println("|| Sorting is as expected ||");
        }
        else {
            System.out.println("|| Sorting is not as expected ||");
        }

        Assert.assertEquals(isSortedByNameAZ, true, "Products are not sorted as expected, from Name (A to Z)");

        productsPage.close();
    }*/



}
