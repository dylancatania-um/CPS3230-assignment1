package task2.pageobjects;

import Alert.Alert;
import org.openqa.selenium.WebDriver;

public class MarketAlertUmPageObject {

    WebDriver driver;

    MarketAlertUmPageObject(WebDriver driver) {  this.driver = driver;   }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle(){
        return driver.getTitle();
    }

}
