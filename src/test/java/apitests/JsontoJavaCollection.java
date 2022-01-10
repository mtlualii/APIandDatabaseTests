package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.baseURI;

public class JsontoJavaCollection {

    @BeforeClass
    public void beforeclass() {

        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @Test
    public void SpartanToMap() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        Map<String,Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);

        String name = (String) jsonDataMap.get("name");
        System.out.println("name = " + name);
        assertEquals(name, "Meta");

        BigDecimal phone = new BigDecimal(String.valueOf(jsonDataMap.get("phone")));
        System.out.println("phone = " + phone);

    }

    @Test
    public void allSpartansToListOfMap() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);

        // we need to deserialize JSON response to List of Maps
        List<Map<String,Object>> allSpartanList = response.body().as(List.class);
        System.out.println("allSpartanList = " + allSpartanList);

        //print second spartan first name
        System.out.println(allSpartanList.get(1).get("name"));

        //Save spartan 3 in a map
        Map<String,Object> spartan3 = allSpartanList.get(2);
        System.out.println(spartan3);

    }

    @Test
    public void regionToMap() {

        Response response = when().get("http://34.228.32.222:1000/ords/hr/regions");

        assertEquals(response.statusCode(), 200);


        //we de-serialize JSON response to Map
        Map<String, Object> regionMap = response.body().as(Map.class);

        System.out.println(regionMap.get("count"));
        System.out.println(regionMap.get("hasMore"));

        List<Map<String,Object>> itemsList = (List<Map<String, Object>>) regionMap.get("items");

        //print first region name

        System.out.println(itemsList.get(0).get("region_name"));

    }



}
