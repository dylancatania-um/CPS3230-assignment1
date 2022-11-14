package RESTcall;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import utils.IStatusCode;

import java.io.*;

public class RestCall {

    protected int statusCode;

    public int createAlert(int alertType, String heading, String description, String url, String imageUrl, int cents, String postedBy) throws IOException {

        //creating payload
        JSONObject payload = new JSONObject();
        payload.put("alertType", alertType);
        payload.put("heading", heading);
        payload.put("description", description);
        payload.put("url", url);
        payload.put("imageUrl", imageUrl);
        payload.put("postedBy", "b96e4c56-188e-4745-b07f-a480e1ae94b1");
        payload.put("priceInCents", cents);

        //setting payload content to application/json
        StringEntity entity = new StringEntity(String.valueOf(payload), ContentType.APPLICATION_JSON);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("https://api.marketalertum.com/Alert");
        request.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(request);
        System.out.println(response.toString());
        setStatusCode(response.getStatusLine().getStatusCode());

        return response.getStatusLine().getStatusCode();
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public int deleteAlerts() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete("https://api.marketalertum.com/Alert?userId=b96e4c56-188e-4745-b07f-a480e1ae94b1");

        CloseableHttpResponse response = httpClient.execute(request);
        System.out.println(response.toString());
        setStatusCode(response.getStatusLine().getStatusCode());

        return response.getStatusLine().getStatusCode();
    }
}
