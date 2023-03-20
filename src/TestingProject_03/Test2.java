package TestingProject_03;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Test2 extends BaseDriver {

    @Test
    public void test2() {

        driver.get("https://shopdemo.e-junkie.com/");

        Actions actions=new Actions(driver);

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement eBook= driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payUsingDebit =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='Payment-Options']/button)[3]")));
        Action action=actions.moveToElement(payUsingDebit).click().build();
        action.perform();

        WebElement payButton = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payButton.click();

        WebElement verificationMessage = driver.findElement(By.xpath("//span[text()='Invalid Email']"));

        Assert.assertEquals("Invalid Email\n" + "Invalid Email\n" + "Invalid Billing Name", verificationMessage.getText());

        waitAndClose();
    }
}
