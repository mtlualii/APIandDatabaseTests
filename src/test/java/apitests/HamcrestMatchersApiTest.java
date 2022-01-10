package apitests;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.hamcrest.Matchers.*;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class HamcrestMatchersApiTest {



    /* TASK
    given accept type is Json
    And path param id is 15
    When user sends a get request to spartans/{id}
    The status code is 200
    And content type is Json
    And json data has following
            "id": 15
            "name": "Meta",
            "gender": "Female",
            "phone": 1938695106

     */


    @Test
    public void OneSpartanWithHamcrest() {

        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("http://34.228.32.222:8000/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and(). assertThat().contentType("application/json")
                .and(). assertThat().body("id",    equalTo(15),
                          "name",   equalTo("Meta"),
                                               "gender", equalTo("Female"),
                                               "phone",  equalTo(1938695106));

    }

    @Test
    public void teacherTest() {

        given().accept(ContentType.JSON)
                .and().pathParam("id",10423)
                .when().get("http://api.cybertektraining.com/teacher/{id}")
                .then().
                       assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().header("Content-Length", "236")
                .and().assertThat().header("Date", notNullValue())
                .and().assertThat().body("teachers.firstName[0]",equalTo("Alexander"))
                .and().assertThat().body("teachers.lastName[0]", equalTo("Syrup"))
                .and().assertThat().body("teachers.gender[0]",   equalTo("male"))
                .log().all();

    }

    @Test
    public void teachersWithDepartments() {

        given().accept(ContentType.JSON)
                .and().pathParam("name", "Computer")
                .when().log().all().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then()
                .assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("teachers.firstName", hasItems("Alexander", "Marteen"));

    }


}
