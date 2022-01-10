package POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class Pojo_deserialize {

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("http://34.228.32.222:8000/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //JSON to POJO --> deserialize to java custom class
        //JSON to our spartan class object

        Spartan spartan15 = response.body().as(Spartan.class);

        System.out.println(spartan15);

        System.out.println(spartan15.getName());
        System.out.println(spartan15.getPhone());

    }

    @Test
    public void RegionToPojo() {

        Response response = when().get("http://34.228.32.222:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        //JSON to POJO (regions class)

        Regions regions = response.body().as(Regions.class);

        System.out.println(regions.getHasMore());
        System.out.println(regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());

        List<Item> items = regions.getItems();

        System.out.println(items.get(1).getRegionId());


    }

    @Test
    public void gson_example() {

        Gson gson = new Gson();

        //JSON to Java collections or Pojo --> De-serialization

        String myJsonData = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Map<String,Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println(map);

        Spartan spartan15 = gson.fromJson(myJsonData,Spartan.class);
        System.out.println(spartan15);

        // ---- Serialization ---
        // Java Collection or POJO to Json

        Spartan spartanEU = new Spartan(200, "Mike", "Male", 123123123 );

        String jsonSpartanEU = gson.toJson(spartanEU);
        System.out.println(jsonSpartanEU);

    }
}
