package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.ConnectBD()) {
            statement.execute("CREATE TABLE IF NOT EXISTS `kata1_1_4`.`users` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(123) NULL,\n" +
                    "  `lastName` VARCHAR(255) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.ConnectBD()) {
            statement.execute("DROP TABLE IF EXISTS `users`");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.ConnectBD()) {
            statement.execute("INSERT INTO `kata1_1_4`.`users` (`name`, `lastName`, `age`) VALUES ('" + name + "', '" + lastName + "', '" + age + "')");
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.ConnectBD()) {
            statement.execute("DELETE FROM `kata1_1_4`.`users` WHERE (`id` = '" + id + "')");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.ConnectBD()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM kata1_1_4.users");
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.ConnectBD()) {
            statement.execute("TRUNCATE `kata1_1_4`.`users`");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
