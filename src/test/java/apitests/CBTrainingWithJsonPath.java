package apitests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10423)
                .when().get("/teacher/{id}");

        assertEquals(response.statusCode(),200);

        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.get("teachers.firstName[0]");
        System.out.println("firstName = " + firstName);

        String lastName = jsonPath.get("teachers.lastName[0]");
        System.out.println("lastName = " + lastName);

        // /students/all gives null, so there is no code here.
        



    }


}
