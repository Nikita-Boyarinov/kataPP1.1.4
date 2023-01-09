package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Util {


    public static Statement ConnectBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kata1_1_4", "root", "root");
            return connection.createStatement();
        } catch (Exception e) {
            System.out.println("Соединение с БД не установлено");
        }
        return null;
    }

}

