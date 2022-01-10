package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@54.227.114.195:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from departments");

        //how to find how many rows we have or the query
        //go to last row
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        resultSet.beforeFirst();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }


    @Test
    public void MetaDataExample() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from employees");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User = " + dbMetadata.getUserName());
        System.out.println("Database Product name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();
        System.out.println("rsMetadata = " + rsMetadata);

        //how many columns we have?
        int colCount = rsMetadata.getColumnCount();
        System.out.println("colCount = " + colCount);

        //column name
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        //print all the colum names dynamically

        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }





        resultSet.close();
        statement.close();
        connection.close();
    }


}
