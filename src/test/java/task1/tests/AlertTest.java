package task1.tests;

import RESTcall.RestCall;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Alert.Alert;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.openqa.selenium.WebDriver;
import task1.pageobjects.AmazonPageObject;
import utils.IAlertType;
import utils.IStatusCode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AlertTest {

    @BeforeEach
    public void setup() {

    }

    @AfterEach
    public void teardown() {

    }

    @Test
    public void testCreateAlert() {
        //setup
        Alert alert = new Alert(IAlertType.ELECTRONICS, "Jumper Windows 11 Laptop",
                            "Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD", "https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth",
                            "https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg", 24999, "6f14f0e3-2d42-4beb-86b0-674127b1be29");
//        RestCall restCall = Mockito.mock(RestCall.class);
//        WebDriver driver = Mockito.mock(WebDriver.class);
//        alert.setDriver(driver);
//
//        //dummy data
//        alert.setAlertType(IAlertType.ELECTRONICS);
//        alert.setUrl("https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
//        alert.setDescription("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD");
//        alert.setHeading("Jumper Windows 11 Laptop");
//        alert.setPostedBy("6f14f0e3-2d42-4beb-86b0-674127b1be29");
//        alert.setImageUrl("https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
//        alert.setPriceInCents(24999);

        //alert

//        int result = alert.createAlert(alert.getAlertType(), alert.getHeading(),
//                alert.getDescription(), alert.getUrl(), alert.getImageUrl(), alert.getPriceInCents(), alert.getPostedBy());

//        System.out.println("created alert, status code: " +restCall.getStatusCode());

//        Assertions.assertEquals(IStatusCode.CREATED, result);
    }

    @Test
    public void testAmazonSearchBox() {
        //setup
        AmazonPageObject amazonPageObject = Mockito.mock(AmazonPageObject.class);

//        amazonPageObject.SearchBox("");

        //exercise

        //verify
    }

    @Test
    public void testCreateAlertCreated() throws IOException {
        //setup
        Alert alert = new Alert();
        RestCall restCall = Mockito.mock(RestCall.class);
        WebDriver driver = Mockito.mock(WebDriver.class);
        alert.setDriver(driver);

        //dummy data
        alert.setAlertType(IAlertType.ELECTRONICS);
        alert.setUrl("https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
        alert.setDescription("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD");
        alert.setHeading("Jumper Windows 11 Laptop");
        alert.setPostedBy("6f14f0e3-2d42-4beb-86b0-674127b1be29");
        alert.setImageUrl("https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
        alert.setPriceInCents(24999);

        Mockito.when(restCall.createAlert(alert.getAlertType(), alert.getHeading(),
                alert.getDescription(), alert.getUrl(), alert.getImageUrl(), alert.getPriceInCents(), alert.getPostedBy())).thenReturn(201);

        //exercise
        int result = restCall.createAlert(alert.getAlertType(), alert.getHeading(),
                alert.getDescription(), alert.getUrl(), alert.getImageUrl(), alert.getPriceInCents(), alert.getPostedBy());

        System.out.println("Alert created. Status code: " +restCall.getStatusCode());

        //verify
        Assertions.assertEquals(IStatusCode.CREATED, result);
    }

    @Test
    public void testCreateAlertFromAmazonThreeTimes() throws IOException {
        //setup
        Alert alert = Mockito.mock(Alert.class);
        RestCall restCall = Mockito.mock(RestCall.class);

        for(int i = 1; i<=3; i++) {
            alert.createAlertFromAmazon();
        }

        //Here, the invocation of each method of class Alert is being the SUT
        Collection<Invocation> invocations = Mockito.mockingDetails(alert).getInvocations();
        int numberOfCalls = invocations.size();

        Assertions.assertEquals(3, numberOfCalls);
    }

    @Test
    public void testCreateAlertFromAmazonFiveTimes() throws IOException {
        //setup
        Alert alert = Mockito.mock(Alert.class);
        RestCall restCall = Mockito.mock(RestCall.class);

        for(int i = 1; i<=5; i++) {
            alert.createAlertFromAmazon();
        }

        //Here, the invocation of each method of class Alert is being the SUT
        Collection<Invocation> invocations = Mockito.mockingDetails(alert).getInvocations();
        int numberOfCalls = invocations.size();

        Assertions.assertEquals(5, numberOfCalls);
    }

    @Test
    public void testCreateAlertBadRequestError() throws IOException {
        //setup
        Alert alert = new Alert();
        RestCall restCall = Mockito.mock(RestCall.class);

        alert.setAlertType(IAlertType.ELECTRONICS);
        alert.setUrl("https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
        alert.setDescription("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD");
        alert.setHeading("Jumper Windows 11 Laptop");
        alert.setPostedBy("this-is-an-invalid-id"); //Since it does not exist, then it will render the alert bad
        alert.setImageUrl("https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
        alert.setPriceInCents(24999);

        Mockito.when(restCall.createAlert(alert.getAlertType(), alert.getHeading(),
                alert.getDescription(), alert.getUrl(), alert.getImageUrl(), alert.getPriceInCents(), alert.getPostedBy())).thenReturn(400);

        //exercise
        int result = restCall.createAlert(alert.getAlertType(), alert.getHeading(),
                alert.getDescription(), alert.getUrl(), alert.getImageUrl(), alert.getPriceInCents(), alert.getPostedBy());

        //verify
        Assertions.assertEquals(IStatusCode.BAD_REQUEST, result);
    }

    @Test
    public void testCreateAlertUnsupportedMediaTypeError() throws IOException {

        //Setup
        Alert alert = new Alert();
        RestCall restCall = Mockito.mock(RestCall.class);

        //dummy data
        alert.setAlertType(IAlertType.ELECTRONICS);
        alert.setUrl("https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth");
        alert.setDescription("Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD");
        alert.setHeading("Jumper Windows 11 Laptop");
        alert.setPostedBy("this-is-an-invalid-id"); //Since it does not exist, then it will render the alert bad
        alert.setImageUrl("https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg");
        alert.setPriceInCents(24999);

        Mockito.when(restCall.createAlert(alert.getAlertType(), alert.getHeading(),
                alert.getDescription(), alert.getUrl(), alert.getImageUrl(), alert.getPriceInCents(), alert.getPostedBy())).thenReturn(415);

        //exercise
        int result = restCall.createAlert(alert.getAlertType(), alert.getHeading(),
                alert.getDescription(), alert.getUrl(), alert.getImageUrl(), alert.getPriceInCents(), alert.getPostedBy());

        //verify
        Assertions.assertEquals(IStatusCode.UNSUPPORTED_MEDIA_TYPE, result);
    }

    @Test
    public void testDeleteAlertsOk() throws IOException {
        //setup
        RestCall restCall = Mockito.mock(RestCall.class);
        Mockito.when(restCall.deleteAlerts()).thenReturn(200);

        //exercise
        int result = restCall.deleteAlerts();

        //verify
        Assertions.assertEquals(IStatusCode.OK, result);
    }

    @Test
    public void testDeleteAlertsBadRequestError() throws IOException {
        //setup
        RestCall restCall = Mockito.mock(RestCall.class);
        Mockito.when(restCall.deleteAlerts()).thenReturn(400);

        //exercise
        int result = restCall.deleteAlerts();

        //verify
        Assertions.assertEquals(IStatusCode.BAD_REQUEST, result);
    }

    @Test
    public void testDeleteAlertsInternalServerError() throws IOException {
        //setup
        RestCall restCall = Mockito.mock(RestCall.class);
        Mockito.when(restCall.deleteAlerts()).thenReturn(500);

        //exercise
        int result = restCall.deleteAlerts();

        //verify
        Assertions.assertEquals(IStatusCode.INTERNAL_SERVER_ERROR, result);
    }

    @Test
    public void testDeleteAlertsServiceUnavailableError() throws IOException {
        //setup
        RestCall restCall = Mockito.mock(RestCall.class);
        Mockito.when(restCall.deleteAlerts()).thenReturn(503);

        //exercise
        int result = restCall.deleteAlerts();

        //verify
        Assertions.assertEquals(IStatusCode.SERVICE_UNAVAILABLE, result);
    }
}
