package TestingProject_03;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseDriver;
import utility.MyFunction;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Test3 extends BaseDriver {

    @Test
    public void test3() {

        driver.get("https://shopdemo.e-junkie.com/");

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement eBook= driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payUsingDebit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Payment-Button CC']")));
        payUsingDebit.click();

        WebElement iframe2 = driver.findElement(By.xpath("//iframe[@title='Güvenli kart ödeme giriş çerçevesi']"));
        driver.switchTo().frame(iframe2);

        WebElement cardNumberBox = driver.findElement(By.xpath("(//input[@class='InputElement is-empty Input Input--empty'])[1]"));
        cardNumberBox.sendKeys("1111 1111 1111 1111");

        driver.switchTo().parentFrame();

        WebElement verificationMessage = driver.findElement(By.xpath("//span[text()='Kart numaranız geçersiz.']"));

        Assert.assertEquals("Kart numaranız geçersiz.", verificationMessage.getText());

        waitAndClose();
    }
}
