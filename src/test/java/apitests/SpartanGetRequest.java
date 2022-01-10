package apitests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class SpartanGetRequest {

    String spartanUrl = "http://34.228.32.222:8000";

    @Test
    public void test1() {

        Response response = when().get(spartanUrl+"/api/spartans");

        System.out.println(response.statusCode());
        response.prettyPrint();
    }

    /* TASK
    WHen users sends a get req to /api/spartans/3
    Then status code should be 200
    And content type should be application/json
    and json body should contain Fidole
     */

    @Test
    public void test2() {

        Response response = when().get(spartanUrl+"/api/spartans/3");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertTrue(response.body().asString().contains("Fidole"));

    }

    /* TASK
    Given no headers provided
    When User sends GET request to /api/hello
    Then response status code should be 200
    And Content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
     */

    @Test
    public void test3() {

        Response response = when().get(spartanUrl+"/api/hello");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "text/plain;charset=UTF-8");

        //verify we have headers named date
        System.out.println(response.header("Content-Length"));
        System.out.println(response.header("Date"));

        Assert.assertTrue(response.headers().hasHeaderWithName("Content-Length"));
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));


        //verify length 17
        Assert.assertEquals(response.header("Content-Length"), "17");

        //verify hello from Sparta

        Assert.assertTrue(response.body().asString().contains("Hello from Sparta"));




    }


}
