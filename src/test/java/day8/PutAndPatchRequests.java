package day8;

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

import static io.restassured.RestAssured.baseURI;

public class PutAndPatchRequests {


    @BeforeClass
    public void beforeClass() {

        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @Test
    public void PutRequest() {

        //Create one map for the put request json body

        Map<String, Object> putRequestMap = new HashMap<>();

        putRequestMap.put("name", "PutName");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 1231311231l);

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 90)
                .body(putRequestMap).
                when()
                .put("/api/spartans/{id}").
                then().log().all()
                .assertThat().statusCode(204);

    }

    @Test
    public void patchRequest() {

        //Create one map for the put request json body

        Map<String, Object> putRequestMap = new HashMap<>();

        putRequestMap.put("name", "TJ");

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 90)
                .body(putRequestMap).
        when()
                .patch("/api/spartans/{id}").
        then().log().all()
                .assertThat().statusCode(204);


    }
}

