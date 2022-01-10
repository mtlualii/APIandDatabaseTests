package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@54.227.114.195:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from countries");
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        List<Map<String, Object>> queryData = new ArrayList<>();

        int colCount = rsMetadata.getColumnCount();

        while (resultSet.next()) {
            Map<String,Object> row = new HashMap<>();
            //put information into map
            for(int i = 1; i<=colCount; i++) {
                row.put(rsMetadata.getColumnName(i), resultSet.getObject(i));
            }
            //add your map to your list
            queryData.add(row);
        }

        for (Map<String, Object> row: queryData) {
            System.out.println(row);
        }














        resultSet.close();
        statement.close();
        connection.close();


    }
}
