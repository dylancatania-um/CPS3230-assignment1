import Alert.Alert;
import RESTcall.RestCall;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {

        Alert alert = new Alert();

        //screen scrapes 5 alerts
        alert.scrapeFiveAlerts();
    }
}
