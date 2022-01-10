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

import org.testng.annotations.Test;

public class SpartanBasicAuth {

    @Test
    public void test1() {

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("editor", "editor")
                .when()
                .get("http://54.198.216.176:8000/api/spartans")
                .then().log().all()
                .statusCode(200);

    }



}
