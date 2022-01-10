package POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestDemo {

    @BeforeClass
    public void beforeclass() {
        baseURI = "http://34.228.32.222:8000";
    }

    /* given accept type and content type is JSON
       and request json body is:
            {
            "gender": "Male",
            "name": "MikeEU",
            "phone": 8877445596
            }
        when user sends POST request to '/api/spartans'
        then status code 201
        And content type should be application/json
        And json payload/response should contain:
        "A Spartan is Born!" message
        and same data what is posted
     */

    @Test
    public void test1() {   // First way - NOT RECOMMENDED // Using string and record information to it

        String jsonBody = "{\n" +
                "            \"gender\": \"Male\",\n" +
                "            \"name\": \"MikeEU\",\n" +
                "            \"phone\": 8877445596\n" +
                "            }";

        Response response = given()
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/api/spartans");

        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(), "application/json");

        //verify success message
        assertEquals(response.path("success"), "A Spartan is Born!");

        //assertion for spartan data
        String name = response.path("data.name");
        String gender = response.path("data.gender");
        long phone = response.path("data.phone");

        assertEquals(name, "MikeEU");
        assertEquals(gender, "Male");
        assertEquals(phone, 8877445596l);
    }

    @Test
    public void test2() {   // Second Way

        //create a map to keep request json body information
        Map<String, Object> requestMap = new HashMap<>();

        //adding value that we want top post
        requestMap.put("name", "MikeEU2");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 8877445596l);

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)  // we send a map but it is automatically serialized! it converts map structure to Json automatically!
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .and()
                .body("success", equalTo("A Spartan is Born!"),
                        "data.name", equalTo("MikeEU2"),
                        "data.gender", equalTo("Male"),
                        "data.phone", equalTo(8877445596l));
    }

    @Test
    public void test3() {   // Third Way

        Spartan spartanEU = new Spartan();

        spartanEU.setName("MikeEU3");
        spartanEU.setGender("Male");
        spartanEU.setPhone(8877445596l);

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(spartanEU)  // we send a map but it is automatically serialized! it converts map structure to Json automatically!
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .and()
                .body("success", equalTo("A Spartan is Born!"),
                        "data.name", equalTo("MikeEU3"),
                        "data.gender", equalTo("Male"),
                        "data.phone", equalTo(8877445596l));
    }

    @Test
    public void test4() {   // Third Way

        Spartan spartanEU = new Spartan();

        spartanEU.setName("MikeEU4");
        spartanEU.setGender("Male");
        spartanEU.setPhone(8877445596l);

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(spartanEU)  // we send a map but it is automatically serialized! it converts map structure to Json automatically!
                .when()
                .post("/api/spartans");

        int id = response.path("data.id");
        System.out.println(id);

        // after post request send a get request to generated Spartan - last thing for POST req

            given().accept(ContentType.JSON)
                    .pathParam("id", id)
                    .when().get("/api/spartans/{id}")
                    .then().statusCode(200).log().all();


    }
} 
