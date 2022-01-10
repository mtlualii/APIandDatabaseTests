package day8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeleteRequest {

    @BeforeClass
    public void beforeClass() {

        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @Test
    public void DeleteRequest() {

        //Create one map for the put request json body

        Random random = new Random();

        int idToDelete = random.nextInt(67)+1;
        System.out.println("idToDelete = " + idToDelete);

        Map<String, Object> putRequestMap = new HashMap<>();

        given()
                .pathParam("id", idToDelete).
        when()
                .delete("/api/spartans/{id}").
        then()
                .assertThat().statusCode(204);

    }

}
