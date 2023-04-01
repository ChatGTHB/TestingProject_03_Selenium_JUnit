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


public class Test4 extends BaseDriver {

    @Test
    public void test4() {

        driver.get("https://shopdemo.e-junkie.com/");

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement eBook= driver.findElement(By.linkText("Ebook"));
        eBook.click();

        WebElement eBookAddToCart = driver.findElement(By.xpath("//button[@class='view_product']"));
        eBookAddToCart.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']")));
        driver.switchTo().frame(iframe);

        WebElement payUsingDebit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Payment-Button CC']")));
        payUsingDebit.click();

        WebElement eMail = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        eMail.sendKeys("email@email.com");

        WebElement confirmMail = driver.findElement(By.xpath("//input[@placeholder='Confirm Email']"));
        confirmMail.sendKeys("email@email.com");

        WebElement name = driver.findElement(By.xpath("//input[@placeholder='Name On Card']"));
        name.sendKeys("Name");

        WebElement phone = driver.findElement(By.xpath("(//input[@placeholder='Optional'])[1]"));
        phone.sendKeys("123456789");

        WebElement company = driver.findElement(By.xpath("(//input[@placeholder='Optional'])[2]"));
        company.sendKeys("Company");

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

        driver.get("https://www.fatfreecartpro.com/ecom/rp.php?rdffc=true&txn_id=st-ch_3Mnh2JFW" +
                "SmRjvnlt0yhpKJvP&payer_email=email%40email.com&client_id=341695&c_id=169962827&c_" +
                "enc=be73fe090a382edef63b25418d57b6d3&cart_metadata=%7B%22gtag%22%3A%7B%22gtag%22%3A%22UA" +
                "-273877-2%22%2C%22_ga%22%3A%222.226775773.1118002772.1679312268-1937456812." +
                "1679312268%22%7D%2C%22fbp%22%3A%7B%22fbp%22%3A%221714673711932838%22%7D%2C%22cart_source%22%3A%22" +
                "https%3A%2F%2Fshopdemo.e-junkie.com%2F%22%2C%22em_updates" +
                "%22%3Atrue%7D&firstLoad=true&&pending_reason=" +
                "&_ga=2.226775773.1118002772.1679312268-1937456812.1679312268&&gajs=&auser=&abeacon=&");


        WebElement payConfirmationMessage= driver.findElement(By.xpath("//span[@ class='green_text_margin']"));

        Assert.assertEquals("your order is confirmed. Thank you!", payConfirmationMessage.getText());

        waitAndClose();
    }
}




















