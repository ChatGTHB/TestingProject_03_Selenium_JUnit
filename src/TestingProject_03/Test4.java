package TestingProject_03;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.BaseDriver;

public class Test4 extends BaseDriver {

    @Test
    public void test4() {

        driver.get("https://shopdemo.e-junkie.com/");

        WebElement eBook = driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payUsingDebit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Payment-Button CC']")));
        payUsingDebit.click();

        String mail = randomGenerator.internet().emailAddress();

        WebElement eMail = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        eMail.sendKeys(mail);

        WebElement confirmMail = driver.findElement(By.xpath("//input[@placeholder='Confirm Email']"));
        confirmMail.sendKeys(mail);

        WebElement name = driver.findElement(By.xpath("//input[@placeholder='Name On Card']"));
        name.sendKeys(randomGenerator.name().fullName());

        WebElement phone = driver.findElement(By.xpath("(//input[@placeholder='Optional'])[1]"));
        phone.sendKeys(randomGenerator.phoneNumber().cellPhone());

        WebElement company = driver.findElement(By.xpath("(//input[@placeholder='Optional'])[2]"));
        company.sendKeys(randomGenerator.company().name());

        WebElement iframe2 = driver.findElement(By.xpath("//iframe[@title='Güvenli kart ödeme giriş çerçevesi']"));
        driver.switchTo().frame(iframe2);

        WebElement cardNumberBox = driver.findElement(By.xpath("(//input[@class='InputElement is-empty Input Input--empty'])[1]"));
        cardNumberBox.sendKeys("4242 4242 4242 4242");

        WebElement cardMonthYear = driver.findElement(By.xpath("//input[@placeholder='AA / YY']"));
        cardMonthYear.sendKeys("1223");

        WebElement cardCVC = driver.findElement(By.xpath("//input[@placeholder='CVC']"));
        cardCVC.sendKeys("000");

        driver.switchTo().parentFrame();

        WebElement payButton = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payButton.click();

        wait.until(ExpectedConditions.titleContains("E-junkie - Thank you"));

        WebElement payConfirmationMessage = driver.findElement(By.xpath("//span[@ class='green_text_margin']"));

        Assert.assertTrue("The process was not true", payConfirmationMessage.getText().contains("your order is confirmed."));

        waitAndClose();
    }
}




















