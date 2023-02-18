package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class TabsTests {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\drago\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://kitchen.applitools.com/ingredients/links");
        driver.findElement(By.id("button-the-kitchen-table")).click();
        //driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
        List<String> tabs = new ArrayList (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println(driver.getCurrentUrl());
        driver.switchTo().window(tabs.get(0));
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.id("button-the-kitchen-table")).click();
        tabs = new ArrayList (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        System.out.println(driver.getCurrentUrl());
        driver.close();
        driver.quit();
        //assertTrue(driver.findElement(By.id("fruits-vegetables")).isDisplayed());
    }
}





