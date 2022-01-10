package day8;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;


public class JsonSchemaValidationDemo {

    @BeforeClass
    public void beforeClass() {

        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @Test
    public void JsonSchemaValidatinForSpartan() {

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 10).
                when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));


    }
}
