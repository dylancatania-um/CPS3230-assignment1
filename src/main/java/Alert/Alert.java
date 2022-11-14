package Alert;

import RESTcall.RestCall;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import task1.pageobjects.AmazonPageObject;
//import task1.pageobjects.MaltaparkPageObject;
import utils.IAlertType;
import utils.IStatusCode;

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

    public WebDriver getDriver() {
        return driver;
    }

//    public void setStatusCode(int  statusCode) {
//        this.statusCode = statusCode;
//    }
//
    public int getStatusCode() {
        return statusCode;
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

//    public void createAlertFromMaltaparkNtimes(int n){
//        for(int i = 0; i < n; i++) {
//            createAlertFromMaltapark();
//        }
//    }

    public void createAlertFromAmazonNtimes(int n) throws IOException {
        for(int i = 0; i <n; i++){
            createAlertFromAmazon();
        }
    }

    public int createAlertFromAmazon() throws IOException {

        System.setProperty("webdriver.chrome.driver", "C:\\webdriver/chromedriver.exe");
        driver = new ChromeDriver();

        //Go to google and disable cookies dialog
        driver.get("https://www.amazon.com");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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

//    public void createAlertFromMaltapark() {
//
//        MaltaparkPageObject maltaparkPageObject = new MaltaparkPageObject(driver);
//
//        maltaparkPageObject.SearchBox("vw polo");
////      maltaparkPageObject.SelectItem();
////
//        WebElement item = driver.findElement(By.className("item"));
//        item.getAttribute("href");
//        item.click();
//
////        try {
////            Thread.sleep(2000);
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        }
////        List<WebElement> classifiedList = driver.findElements(By.className("ui items listings classifieds clearfix gridview"));
////
//////        classifiedList.
////        System.out.println("number of elements in classified: " + classifiedList.size());
////        classifiedList.get(0).findElement(By.className("item e4 e3 e2 ")).findElement(By.className("image"))
////                        .findElement(By.className("innerimage")).findElement(By.className("imagelink")).getAttribute("href");
////        classifiedList.get(0).click();
//
////        classifiedList.
//            //------------------------------
//
////        WebElement item = driver.findElement(By.xpath("//div[@class='item e4 e3 e2 ']/input/div[1]/div[1]/a"));
////        System.out.println("this is the fucking item " + item);
////        item.click();
//
////        WebElement carAndPartsCategory = driver.findElement(By.xpath("/html/body/div[2]/header/div[2]/div/div/ul/li[4]/a"));
////        carAndPartsCategory.click();
////
//////        WebElement item = driver.findElement(By.className("item featured e4 e3 e2 i0"));
////        WebElement item = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div/div[3]/div[1]/div[1]/div[1]/a"));
////        item.click();
//
//            String url = driver.getCurrentUrl();
//            String title = driver.findElement(By.id("top-title")).getText();
//
//
//            String stringPriceInEuro = driver.findElement(By.className("top-price")).getText();
//            int priceInEuro = Integer.parseInt(stringPriceInEuro) * 100;
//
//            String description = driver.findElement(By.className("readmore-wrapper")).getText();
//            String imageUrl = driver.findElement(By.className("fancybox")).findElement(By.xpath("img")).getAttribute("src");
//
//            String postedBy = "b96e4c56-188e-4745-b07f-a480e1ae94b1";
//
//            //send request
//            RestCall request = new RestCall();
//        try {
//            request.createAlert(IAlertType.PROPERTY_FOR_RENT, title, description, url, imageUrl, priceInEuro, postedBy);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

}
