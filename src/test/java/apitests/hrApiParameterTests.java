package apitests;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;

public class hrApiParameterTests {

    @BeforeClass
    public void beforeClass() {

        baseURI = ConfigurationReader.get("hr_api_url");

    }

    /*
    Given accept type is Json
    Ad parameters q = "region_id":2
    When users sends a GET req to "/countries"
    Then status code is 200
    And Content Type is application/json
    And payload should contain "United States of America"
    {"region_id":2}
     */

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("United States of America"));

    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));




    }

}
