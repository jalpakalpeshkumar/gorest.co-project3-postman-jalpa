package in.co.gorest.gorestinfo;

import in.co.gorest.testbase.TestBase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UserDeleteTest extends TestBase {

    private static final String BASE_URL = "https://gorest.co.in/public/v2/users";
    private static final String AUTH_TOKEN = "ACCESS-TOKEN";

    @Test
    public void testDeleteUser() throws Exception {
        int userId = 16;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete request = new HttpDelete(BASE_URL + "/" + userId);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", "Bearer " + AUTH_TOKEN);

        CloseableHttpResponse response = client.execute(request);

        // Assert that the user was deleted successfully (Check status code)
        assertEquals(204, response.getStatusLine().getStatusCode(), "User deletion failed");

        // Close the HTTP client
        client.close();
    }
}
