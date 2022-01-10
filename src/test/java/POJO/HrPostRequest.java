package POJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class HrPostRequest {

    @BeforeClass
    public void beforeclass() {

        baseURI = ConfigurationReader.get("hr_api_url");

    }

    @Test
    public void PostRegion1() {

        RegionPost regionPost = new RegionPost(10,"Cybertek Germany");

        given().log().all()
                .and()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(regionPost)
                .when().post("/regions/")
                .then().log().all()
                .statusCode(201)
                .body("region_id", equalTo(10));
    }





}
