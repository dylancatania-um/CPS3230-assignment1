package task1.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonPageObject {

    WebDriver driver;

    public AmazonPageObject(WebDriver driver) { this.driver = driver; }

    public void SearchBox(String search) {
        WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
        searchField.click();
        searchField.sendKeys(search);
        searchField.submit();
    }
    public void OpenFirstProduct() {
        WebElement selectImage = driver.findElement(By.className("s-image"));
        selectImage.click();
    }
}
