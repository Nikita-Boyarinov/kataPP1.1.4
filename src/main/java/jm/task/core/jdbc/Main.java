package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 34);
        userDaoJDBC.saveUser("Artem", "Rogov", (byte) 19);
        userDaoJDBC.saveUser("Nick", "Roger", (byte) 25);
        userDaoJDBC.saveUser("Slava", "Kayne", (byte) 28);
        System.out.println(userDaoJDBC.getAllUsers());
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
