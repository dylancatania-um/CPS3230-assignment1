package Alert;

import RESTcall.RestCall;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import task1.pageobjects.AmazonPageObject;
import utils.Constant;
import utils.IAlertType;
import java.io.IOException;

public class Alert {

    protected int alertType;
    protected String heading;
    protected String description;
    protected String url;
    protected String imageUrl;
    protected int priceInCents;
    protected String postedBy;

    protected WebDriver driver;

    protected int statusCode;

    public Alert(int alertType, String heading, String description, String url, String imageUrl, int priceInCents, String postedBy) {
        this.alertType = alertType;
        this.heading = heading;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.priceInCents = priceInCents;
        this.postedBy = postedBy;
    }

    public Alert() {}

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }

    public int getAlertType() {
        return alertType;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl(){
        return url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }
    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
    public String getPostedBy() {
        return postedBy;
    }

    public void scrapeFiveAlerts() throws IOException {
        System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.get("https://www.amazon.com");

        AmazonPageObject amazonPageObject = new AmazonPageObject(driver);

        String searchItem = "vinyl records slipknot";

        driver.findElement(By.id("twotabsearchtextbox")).click();
        amazonPageObject.SearchBox(searchItem);

        //the reason why this loop starts from 2 is where the images are rendered on amazon.com.
        //Thus, in order to loop after every element, this approach had to be done.
        for(int i = 2; i<9; i++) {

            //furthermore, i=5 and i = 6 are skipped due to a sponsored post which does not have all the requirements for an alert to be uploaded (e.g. Price)
            if(i == 5 || i == 6) continue;
            WebElement img = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[1]/div[1]/div["+i+"]/div/div/div/div/div/div[1]/div/div[2]/div/span/a/div/img"));
            img.click();

            String url = driver.getCurrentUrl();

            String title = driver.findElement(By.id("titleSection")).getText();
            String priceInEuro = driver.findElement(By.className("a-price-whole")).getText();
            String priceInCents = driver.findElement(By.className("a-price-fraction")).getText();
            String concatPrice = priceInCents + priceInEuro;

            String description = driver.findElement(By.id("productDescription")).getText();
            String imageUrl = driver.findElement(By.id("landingImage")).getAttribute("src");

            String postedBy = "b96e4c56-188e-4745-b07f-a480e1ae94b1";

            RestCall restCall = new RestCall();
            restCall.createAlert(IAlertType.ELECTRONICS, title, description, url, imageUrl, Integer.parseInt(concatPrice), postedBy);
            System.out.println(restCall.getStatusCode());

            driver.navigate().back();
        }
    }
    //Web scraping of 1 alert on Amazon
    public int createAlertFromAmazon() throws IOException {

        System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_PATH);
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.get("https://www.amazon.com");

        AmazonPageObject amazonPageObject = new AmazonPageObject(driver);

        String searchItem = "vinyl records slipknot";

        driver.findElement(By.id("twotabsearchtextbox")).click();
        amazonPageObject.SearchBox(searchItem);
        amazonPageObject.OpenFirstProduct();

        String url = driver.getCurrentUrl();

        String title = driver.findElement(By.id("titleSection")).getText();
        String priceInEuro = driver.findElement(By.className("a-price-whole")).getText();
        String priceInCents = driver.findElement(By.className("a-price-fraction")).getText();
        String concatPrice = priceInCents + priceInEuro;

        String description = driver.findElement(By.id("productDescription")).getText();
        String imageUrl = driver.findElement(By.id("landingImage")).getAttribute("src");

        String postedBy = "b96e4c56-188e-4745-b07f-a480e1ae94b1";

        //send request
        RestCall restCall = new RestCall();
        restCall.createAlert(getAlertType(), title, description, url, imageUrl, Integer.parseInt(concatPrice), postedBy);
        System.out.println(restCall.getStatusCode());
        return restCall.getStatusCode();
    }
}
