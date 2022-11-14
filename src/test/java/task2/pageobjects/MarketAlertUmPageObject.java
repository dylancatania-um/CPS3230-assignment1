package task2.pageobjects;

import Alert.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MarketAlertUmPageObject {

    WebDriver driver;

    public MarketAlertUmPageObject(WebDriver driver) {  this.driver = driver;   }

//    public void setDriver(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public String getTitle(){
//        return driver.getTitle();
//    }

    public String login() {
        driver.get("https://www.marketalertum.com/Alerts/Login");
        WebElement userId = driver.findElement(By.id("UserId"));
        userId.click();
        userId.sendKeys("b96e4c56-188e-4745-b07f-a480e1ae94b1");
        userId.submit();

        return  driver.getCurrentUrl();
    }
}
