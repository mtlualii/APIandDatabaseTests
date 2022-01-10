package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listofmap_example {

    String dbUrl = "jdbc:oracle:thin:@54.227.114.195:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from regions");

        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", 4000);
        row1.put("job_id", "AD_PRES");

        System.out.println("row1.toString() = " + row1.toString());

        Map<String, Object> row2 = new HashMap<>();
        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochhar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");

        System.out.println("row2.toString() = " + row2.toString());

        System.out.println(row2.get("salary"));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name directly from the list
        System.out.println(queryData.get(0).get("last_name"));



        resultSet.close();
        statement.close();
        connection.close();





    }


    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select first_name, last_name, salary, job_id from employees\n" + "where rownum <6");

        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        resultSet.next();
        Map<String, Object> row1 = new HashMap<>();
        row1.put(rsMetadata.getColumnName(1), resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2), resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3), resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4), resultSet.getString(4));

        System.out.println("row1.toString() = " + row1.toString());

        resultSet.next();
        Map<String, Object> row2 = new HashMap<>();
        row2.put(rsMetadata.getColumnName(1), resultSet.getString(1));
        row2.put(rsMetadata.getColumnName(2), resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3), resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4), resultSet.getString(4));

        System.out.println("row2.toString() = " + row2.toString());

        System.out.println(row2.get("SALARY"));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name directly from the list
        System.out.println(queryData.get(0).get("LAST_NAME"));


        resultSet.close();
        statement.close();
        connection.close();


    }
}


