package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class ScrollTest {

    WebDriver driver;
    @Test
    public void ByPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\drago\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        driver = new ChromeDriver();

        // Launch the application
        driver.get("http://demo.guru99.com/test/guru99home/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement select = driver.findElement(By.id("awf_field-91977689"));
        select.click();

        List<WebElement> listOptions = select.findElements(By.xpath(".//option"));
        for(int i = 0; i < listOptions.size(); i++) {
            if(listOptions.get(i).getText().equals("SAP CO")) {
                listOptions.get(i).click();
                break;
            }
        }

        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.close();
        driver.quit();
    }

    @Test
    public void ByVisibleElement() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\drago\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Launch the application
        driver.get("http://demo.guru99.com/test/guru99home/");

        //Find element by link text and store in variable "Element"
        //WebElement Element = driver.findElement(By.linkText("Linux"));
        WebElement Element = driver.findElement(By.xpath("//a[@href='https://www.guru99.com/unix-linux-tutorial.html']"));

        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", Element);

        driver.close();
        driver.quit();
    }

}
