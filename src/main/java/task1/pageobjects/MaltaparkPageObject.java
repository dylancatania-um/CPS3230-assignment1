//package task1.pageobjects;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public class MaltaparkPageObject {
//
//    WebDriver driver;
//
//    public MaltaparkPageObject(WebDriver driver) { this.driver = driver; }
//
//    public void SearchBox(String search) {
//
//        WebElement searchField = driver.findElement(By.id("search"));
//        searchField.click();
//        searchField.sendKeys(search);
//        searchField.submit();
//    }
//
//    public void SelectItem(){
//        WebElement item = driver.findElement(By.className("item e4 e3 e2 "));
//        item.click();
//    }
//}
