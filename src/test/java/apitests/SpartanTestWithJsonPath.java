package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.baseURI;



public class SpartanTestWithJsonPath {

    @BeforeClass
    public void beforeclass() {

        baseURI = "http://34.228.32.222:8000";

    }


    /*
    given accept type is json
    and path param spartan id is 11
    When user sends a get req to /spartans/{id}
    Then status code is 200
    And content type is Json
    And "id": 11,
        "name": "Nona",
        "gender": "Female",
        "phone": 7959094216

     */

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        int id = response.path("id");
        String name = response.path("name");

        assertEquals(id, 11);
        assertEquals(name, "Nona");

        // assing response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        int idJson = jsonPath.getInt("id");
        String nameJson = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");


        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(idJson,11);
        assertEquals(nameJson, "Nona");
        assertEquals(gender, "Female");
        assertEquals(phone, 7959094216l);

    }



}
