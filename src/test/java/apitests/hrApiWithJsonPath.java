package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.baseURI;

public class hrApiWithJsonPath {

    @BeforeClass
    public void beforeClass() {

        baseURI = ConfigurationReader.get("hr_api_url");

    }

    /*

     */

    @Test
    public void test1() {

        Response response = get("/countries");

        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        List<String> countryIDs = jsonPath.getList("items.country_id");
        System.out.println("countryIDs = " + countryIDs);
        //get all country names where they are region id is equal to

        List<String> countryNamesWithRegionID2 = jsonPath.getList("items.findAll {it.region_id == 2}.country_name");
        System.out.println("countryNamesWithRegionID2 = " + countryNamesWithRegionID2);


    }

    @Test
    public void test2() {
        Response response = given().queryParam("limit", 107)
                            .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        // get me all email of employees who is working as IT_PROG
        List<String> emails = jsonPath.getList("items.findAll {it.job_id == \"IT_PROG\"}.email");
        System.out.println("emails = " + emails);

        //get me first name of who is making highest salary
        List<String> firstNames = jsonPath.getList("items.findAll {it.salary > 10000}.first_name");
        System.out.println("firstNames = " + firstNames);

        //get me first name of who is making highest salary
        String kingName = jsonPath.getString("items.min {it.salary}.first_name");
        System.out.println("kingName = " + kingName);


    }


}
