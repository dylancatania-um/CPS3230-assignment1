import Alert.Alert;
import RESTcall.RestCall;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {

        Alert alert = new Alert();
        RestCall restCall = new RestCall();

        //screen scrapes 5 alerts
//        for(int i = 1; i<=5; i++) {
//            alert.createAlertFromAmazon();
//        }


//        alert.createAlertFromAmazonNtimes(5);

        alert.scrapeFiveAlerts();

    }
}
