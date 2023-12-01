package TestingProject_03;

import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Test3 extends BaseDriver {

    @Test
    public void test3() {

        driver.get("https://shopdemo.e-junkie.com/");

        WebElement eBook= driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payCreditCard= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='Payment-Button CC']")));
        payCreditCard.click();

        WebElement iframeCardNumber= driver.findElement(By.cssSelector("iframe[name^='__privateStripeFrame']"));
        driver.switchTo().frame(iframeCardNumber);

        WebElement cardNumber= driver.findElement(By.cssSelector("[name='cardnumber']"));
        cardNumber.sendKeys("1111 1111 1111 1111");

        driver.switchTo().parentFrame();

        WebElement confirmationMessage= driver.findElement(By.xpath("//span[text()='Your card number is invalid.']"));

        Assert.assertEquals(confirmationMessage.getText(),"Your card number is invalid.");

        waitAndClose();
    }
}
