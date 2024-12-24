package in.co.gorest.gorestinfo;

import in.co.gorest.testbase.TestBase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class UserCreateTest extends TestBase {

    private static final String BASE_URL = "https://gorest.co.in/public/v2/users";
    private static final String AUTH_TOKEN = "ACCESS-TOKEN";
    @Test
    public void testCreateUser() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_URL);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", "Bearer " + AUTH_TOKEN);

        String jsonBody = "{\"name\":\"Tenali Ramakrishna\",\"gender\":\"male\",\"email\":\"tenali.ramakrishna@15ce.com\",\"status\":\"active\"}";
        request.setEntity(new org.apache.http.entity.StringEntity(jsonBody));

        CloseableHttpResponse response = client.execute(request);
        String responseString = EntityUtils.toString(response.getEntity());

        // Assert that the user was created successfully (Check status code)
        assertEquals(201, response.getStatusLine().getStatusCode(), "User creation failed");

        // Close the HTTP client
        client.close();
    }
}
