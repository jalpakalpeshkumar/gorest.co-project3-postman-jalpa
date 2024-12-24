package in.co.gorest.gorestinfo;

import in.co.gorest.testbase.TestBase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UserUpdateTest extends TestBase {

    private static final String BASE_URL = "https://gorest.co.in/public/v2/users";
    private static final String AUTH_TOKEN = "ACCESS-TOKEN";

    @Test
    public void testUpdateUser() throws Exception {
        int userId = 16;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPatch request = new HttpPatch(BASE_URL + "/" + userId);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", "Bearer " + AUTH_TOKEN);

        String jsonBody = "{\"name\":\"Allasani Peddana\",\"email\":\"allasani.peddana@15ce.com\",\"status\":\"active\"}";
        request.setEntity(new org.apache.http.entity.StringEntity(jsonBody));

        CloseableHttpResponse response = client.execute(request);
        String responseString = EntityUtils.toString(response.getEntity());

        // Assert that the user was updated successfully (Check status code)
        assertEquals(200, response.getStatusLine().getStatusCode(), "User update failed");

        // Close the HTTP client
        client.close();
    }
}
