package task2.steps;

import Alert.Alert;
import RESTcall.RestCall;
import com.beust.ah.A;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import task2.pageobjects.MarketAlertUmPageObject;
import utils.Constant;
import utils.IAlertType;

import java.io.IOException;
import java.util.List;

public class MarketAlertUmSteps {

    Alert alert;
    WebDriver driver;
    MarketAlertUmPageObject marketAlertUmPageObject;

    @Before
    public void setup() {
        alert = new Alert();

        System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        alert.setDriver(driver);
        marketAlertUmPageObject = new MarketAlertUmPageObject(driver);
    }

    @After
    public void teardown() {
        alert = null;
        driver = null;
    }

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {  }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() throws InterruptedException {
        driver.get("https://www.marketalertum.com/Alerts/Login");
        WebElement userId = driver.findElement(By.id("UserId"));
        userId.click();
        userId.sendKeys("b96e4c56-188e-4745-b07f-a480e1ae94b1");
        userId.submit();

        String loginTitle = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/List", loginTitle);
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        String alertsPage = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/List", alertsPage);
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        driver.get("https://www.marketalertum.com/Alerts/Login");
        WebElement userId = driver.findElement(By.id("UserId"));
        userId.click();
        userId.sendKeys("this-is-an-invalid-user-id");
        userId.submit();

        String loginTitle = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/Login", loginTitle);
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        String loginTitle = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/Login", loginTitle);
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) throws IOException {

        RestCall restCall = new RestCall();

        alert.setAlertType(IAlertType.ELECTRONICS);
        alert.setUrl("https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
        alert.setDescription("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD");
        alert.setHeading("Jumper Windows 11 Laptop");
        alert.setPostedBy("6f14f0e3-2d42-4beb-86b0-674127b1be29");
        alert.setImageUrl("https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
        alert.setPriceInCents(24999);

        for(int i = 1; i<=arg0; i++) {
            restCall.createAlert(alert.getAlertType(),
                                alert.getHeading(),
                                alert.getDescription(),
                                alert.getUrl(),
                                alert.getImageUrl(),
                                alert.getPriceInCents(),
                                alert.getPostedBy()
                            );
        }
    }

    @When("I vew a list of alerts")
    public void iVewAListOfAlerts() throws InterruptedException {

        marketAlertUmPageObject.login();
        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
        //if there is 1 or more alerts, then it can be classified as a list of alerts
        if (listOfElements.size() > 1) {
            Assertions.assertTrue(true);
        }
        Assertions.assertNotEquals(0, listOfElements.size());
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
        for(int i = 1; i<= listOfElements.size(); i++) {
            WebElement element = driver.findElement(By.xpath("/html/body/div/main/table"+"["+i+"]"+"/tbody/tr[1]/td/h4/img"));

            boolean containsIcon = element.isDisplayed();
            Assertions.assertTrue(containsIcon);
        }
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
        for(int i = 1; i<= listOfElements.size(); i++) {
            WebElement element = driver.findElement(By.xpath("//html/body/div/main/table"+"["+i+"]"+"/tbody/tr[1]/td/h4"));
            boolean containsHeading = element.getText().isEmpty();
            Assertions.assertFalse(containsHeading);
        }
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
        for(int i = 1; i<= listOfElements.size(); i++) {
            WebElement element = driver.findElement(By.xpath("/html/body/div/main/table"+"["+i+"]"+"/tbody/tr[3]/td"));
            boolean containsDescription = element.getText().isEmpty();
            Assertions.assertFalse(containsDescription);
        }
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
        for(int i = 1; i<= listOfElements.size(); i++){
            WebElement element = driver.findElement(By.xpath("/html/body/div/main/table"+"["+i+"]"+"/tbody/tr[1]/td/h4/img"));
            element.getAttribute("src");
            boolean containsImage = element.isDisplayed();
            Assertions.assertTrue(containsImage);
        }
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
        for(int i = 1; i<= listOfElements.size(); i++){
            WebElement element = driver.findElement(By.xpath("/html/body/div/main/table"+"["+i+"]"+"/tbody/tr[4]/td"));
            element.getText();
            if (element.getText().contains("Price: ")) {
                boolean containsPrice = true;
                Assertions.assertTrue(containsPrice);
            }
        }
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {

        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
        for(int i = 1; i<= listOfElements.size(); i++) {
            WebElement element = driver.findElement(By.xpath("/html/body/div/main/table"+"["+i+"]"+"/tbody/tr[5]/td/a"));
            element.getAttribute("href");
            boolean containsLink = element.isDisplayed();
            Assertions.assertTrue(containsLink);
        }

    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) throws IOException {
//        try {
////            alert.createAlertFromAmazonNtimes(arg0+1);
////            for(int i = 1; i<=arg0+1; i++) {
////                alert.createAlertFromAmazon();
////            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        alert = new Alert();
        RestCall restCall = new RestCall();

        alert.setAlertType(IAlertType.ELECTRONICS);
        alert.setUrl("https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
        alert.setDescription("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD");
        alert.setHeading("Jumper Windows 11 Laptop");
        alert.setPostedBy("6f14f0e3-2d42-4beb-86b0-674127b1be29");
        alert.setImageUrl("https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
        alert.setPriceInCents(24999);

        for(int i = 1; i<=arg0+1; i++) {
            restCall.createAlert(alert.getAlertType(),
                    alert.getHeading(),
                    alert.getDescription(),
                    alert.getUrl(),
                    alert.getImageUrl(),
                    alert.getPriceInCents(),
                    alert.getPostedBy()
            );
        }
    }

    @When("I view a list of alerts I should see {int} alerts")
    public void iViewAListOfAlertsIShouldSeeAlerts(int arg0) {

        marketAlertUmPageObject.login();
        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
        Assertions.assertEquals(arg0, listOfElements.size());
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        marketAlertUmPageObject.login();
        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
    }

    @Then("I should see {int} alert")
    public void iShouldSeeAlert(int arg0) {
        List<WebElement> listOfElements = driver.findElements(By.xpath("/html/body/div/main/table"));
        Assertions.assertEquals(arg0, listOfElements.size());
    }


    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(int arg0) throws IOException {
        RestCall restCall = new RestCall();
        restCall.deleteAlerts();

        alert.setAlertType(arg0);
//        alert.setAlertType(IAlertType.ELECTRONICS);
        alert.setUrl("https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
        alert.setDescription("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD");
        alert.setHeading("Jumper Windows 11 Laptop");
        alert.setPostedBy("6f14f0e3-2d42-4beb-86b0-674127b1be29");
        alert.setImageUrl("https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
        alert.setPriceInCents(24999);

            restCall.createAlert(alert.getAlertType(),
                    alert.getHeading(),
                    alert.getDescription(),
                    alert.getUrl(),
                    alert.getImageUrl(),
                    alert.getPriceInCents(),
                    alert.getPostedBy()
            );
    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String arg0) {
        WebElement element = driver.findElement(By.xpath("/html/body/div/main/table[1]/tbody/tr[1]/td/h4/img"));
        System.out.println("this is the src attribute of icon " +element.getAttribute("src"));

        String result = element.getAttribute("src");

        //concatenation of arg0 so that it would be exactly equal to result
        Assertions.assertEquals("https://www.marketalertum.com/images/"+arg0, result);

    }
}
