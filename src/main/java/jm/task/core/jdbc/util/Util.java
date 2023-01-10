package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Util {


    public static Connection ConnectBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/kata1_1_4", "root", "root");

        } catch (Exception e) {
            System.out.println("Соединение с БД не установлено");
        }
        return null;
    }

}

