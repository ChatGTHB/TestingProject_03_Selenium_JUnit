package TestingProject_03;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

public class Test5 extends BaseDriver {

    @Test
    public void test5() {

        driver.get("https://shopdemo.e-junkie.com/");

        WebElement contactUs = driver.findElement(By.xpath("//a[@class='contact']"));
        contactUs.click();

        WebElement name = driver.findElement(By.xpath("//input[@id='sender_name']"));
        name.sendKeys(randomGenerator.name().fullName());

        WebElement eMail = driver.findElement(By.xpath("//input[@id='sender_email']"));
        eMail.sendKeys(randomGenerator.internet().emailAddress());

        WebElement subject = driver.findElement(By.xpath("//input[@id='sender_subject']"));
        subject.sendKeys(randomGenerator.lorem().word());

        WebElement message = driver.findElement(By.xpath("//textarea[@id='sender_message']"));
        message.sendKeys(randomGenerator.lorem().paragraph());

        WebElement sendMessage = driver.findElement(By.xpath("//button[text()='Send Message']"));
        sendMessage.click();

        Assert.assertEquals(wait.until(ExpectedConditions.alertIsPresent()).getText(), "Recaptcha didn't match");

        driver.switchTo().alert().accept();

        waitAndClose();
    }
}
