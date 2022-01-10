package apitests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class review_oscar2 {

    String zipUrl = "http://api.zippopotam.us/";
    String spartanUrl = "http://34.228.32.222:8000/api/spartans";

    @Test
    public void OneSpartanTest() {

        given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("http://34.228.32.222:8000/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and(). assertThat().contentType("application/json")
                .and(). assertThat().body("id",    equalTo(5),
                        "name",   equalTo("Blythe"),
                        "gender", equalTo("Female"),
                        "phone",  equalTo(3677539542l))
                .log().all();

    }

    @Test
    public void zipTest() {
        given().accept(ContentType.JSON)
                .and().pathParam("zipCode", 45414)
                .when().get(zipUrl+"US/{zipCode}")
                .then().assertThat().statusCode(200)
                .and(). assertThat().contentType("application/json")
                .and(). assertThat().body("places.longitude[0]",equalTo("-84.2024"))
                .log().all();

    }

    @Test
    public void collectionTest() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("http://34.228.32.222:8000/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);

        Map<String,Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);

        String name = (String) jsonDataMap.get("name");
        System.out.println("name = " + name);
        assertEquals("Blythe", name);


    }

    @Test // Deserialization to Java
    public void collectionTest2() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipCode", 45414)
                .when().get(zipUrl + "US/{zipCode}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        Map<String,Object> jsonDataMapDayton = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMapDayton);

        List<Map<String, Object>> DaytonPlaces = (List<Map<String, Object>>) jsonDataMapDayton.get("places");
        System.out.println("DaytonPlaces = " + DaytonPlaces);

        assertEquals(DaytonPlaces.get(0).get("state"), "Ohio");
        assertEquals(DaytonPlaces.get(0).get("latitude"),  "39.8285");
        

        






    }


}
