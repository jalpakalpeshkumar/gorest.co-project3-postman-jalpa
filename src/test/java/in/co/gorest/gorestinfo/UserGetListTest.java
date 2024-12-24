package in.co.gorest.gorestinfo;

import in.co.gorest.testbase.TestBase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserGetListTest extends TestBase {

    private static final String BASE_URL = "https://gorest.co.in/public/v2/users";


    @Test
    public void testListUsers() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(BASE_URL);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");


        CloseableHttpResponse response = client.execute(request);
        String responseString = EntityUtils.toString(response.getEntity());

        // Parse the response as a JSON array
        JSONArray users = new JSONArray(responseString);

        // Verify 20 records
        assertEquals(20, users.length(), "Total records should be 20");

        // Verify user with id 2492 has name "Fr. Gatik Ahluwalia"
        boolean user2492Found = false;
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.getInt("id") == 2492) {
                assertEquals("Fr. Gatik Ahluwalia", user.getString("name"));
                user2492Found = true;
            }
        }
        assertTrue(user2492Found, "User with id 2492 should be found");

        // Verify user with id 2483 has email "birjesh_acharya@brown.net"
        boolean user2483Found = false;
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.getInt("id") == 2483) {
                assertEquals("birjesh_acharya@brown.net", user.getString("email"));
                user2483Found = true;
            }
        }
        assertTrue(user2483Found, "User with id 2483 should be found");

        // Verify all users have status = "active"
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            assertEquals("active", user.getString("status"), "User with id " + user.getInt("id") + " should have status 'active'");
        }

        // Verify user with id 2472 has gender "female"
        boolean user2472Found = false;
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.getInt("id") == 2472) {
                assertEquals("female", user.getString("gender"));
                user2472Found = true;
            }
        }
        assertTrue(user2472Found, "User with id 2472 should have gender 'female'");

        // Verify user with id 2471 has gender "male"
        boolean user2471Found = false;
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.getInt("id") == 2471) {
                assertEquals("male", user.getString("gender"));
                user2471Found = true;
            }
        }
        assertTrue(user2471Found, "User with id 2471 should have gender 'male'");

        // Close the HTTP client
        client.close();
    }

}
