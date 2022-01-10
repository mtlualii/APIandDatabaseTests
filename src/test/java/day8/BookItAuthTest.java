package day8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class BookItAuthTest {

    @BeforeClass
    public void beforeClass() {

        baseURI = "https://cybertek-reservation-api-qa2.herokuapp.com";

    }

    String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDIwIiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.jGs3jHQtTzXgRYVyIgciD_FdCEVpHyx05sp4m6t8wR8";

    @Test
    public void getAllCampuses() {

        Response response = given().header("Authorization", accessToken).
                when().get("/api/campuses");

        response.prettyPrint();
        System.out.println(response.statusCode());

    }

}
