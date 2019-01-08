package cn.xiaows.applogs.dubbo.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试hive jdbc连接
 *
 * @author: xiaows
 * @create: 2019-01-08 14:57
 * @version: v1.0
 */
public class HiveTest {
    public static void main(String[] args) {

        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            Connection connection = DriverManager.getConnection("jdbc:hive2://172.22.1.9:10000/app_logs", "root", "");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from ext_app_log");
            // ResultSet rs = statement.executeQuery("select count(1) from ext_app_log");
            int i = 0;
            while (rs.next()) {
                System.out.println(rs.getString(1));
                i++;
            }
            System.out.println(i);
            rs.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
