package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class review_oscar {

    String hrUrl = "http://34.228.32.222:1000/ords/hr";
    String zipUrl = "http://api.zippopotam.us/";
    String spartanUrl = "http://34.228.32.222:8000/api/spartans";

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().get(hrUrl + "/employees");

        response.prettyPrint();

    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when().get(hrUrl + "/regions");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        assertTrue(response.body().asString().contains("Europe"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals(response.header("Transfer-Encoding"), "chunked");


    }

    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON)
                .when().get(zipUrl + "DE/52349");

        assertEquals(response.statusCode(), 200);
        assertTrue(response.body().asString().contains("Nordrhein-Westfalen"));
        assertEquals(response.header("Content-Type"), "application/json");


    }

    @Test
    public void test4() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "male")
                .and().queryParam("nameContains", "m")
                .when().get(spartanUrl + "/search");

        assertEquals(response.statusCode(), 200);

        assertTrue(response.body().asString().contains("Tedmund"));

    }

    @Test
    public void test5() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get(hrUrl + "/employees");

        assertEquals(response.statusCode(), 200);





    }


}
