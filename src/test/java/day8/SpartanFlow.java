package day8;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanFlow {

    @BeforeClass
    public void beforeClass() {

        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @Test(priority = 1)
    public void POSTNewSpartan() {

    }

    @Test(priority = 2)
    public void PUTExistingSpartan() {

    }

    @Test(priority = 2)
    public void PATCHExistingSpartan() {

    }

    @Test(priority = 4)
    public void GETThatSpartan() {


    }

    @Test(priority = 5)
    public void DeleteThatSpartan() {

        //Create one map for the put request json body

        Map<String, Object> putRequestMap = new HashMap<>();

        given()
                .pathParam("id", 111).
                when()
                .delete("/api/spartans/{id}").
                then()
                .assertThat().statusCode(204);

    }




}
