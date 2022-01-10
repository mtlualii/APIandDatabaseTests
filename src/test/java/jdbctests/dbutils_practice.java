package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {

    @Test
    public void test1() {
        //create connection
        DBUtils.createConnection();
        List<Map<String,Object>> queryResult = DBUtils.getQueryResultMap("select * from employees");

        //printing the result
        for (Map<String, Object> map : queryResult) {
            System.out.println(map.toString());
        }

        //close.connection
        DBUtils.destroy();

    }

    @Test
    public void test2() {
        DBUtils.createConnection();

        Map<String, Object> rowMap = DBUtils.getRowMap("select first_name, last_name, salary, job_id from employees\n" + "where employee_id = 100");

        System.out.println(rowMap.toString());

        DBUtils.destroy();
    }

}
