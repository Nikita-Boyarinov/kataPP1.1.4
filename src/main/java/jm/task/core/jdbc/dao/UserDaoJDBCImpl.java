package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.StoredProcedureQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection con = Util.ConnectBD()) {
            String sql = "CREATE TABLE IF NOT EXISTS `kata1_1_4`.`users` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(123) NULL,\n" +
                    "  `lastName` VARCHAR(255) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void dropUsersTable() {
        try (Connection con = Util.ConnectBD()) {
            String sql = "DROP TABLE IF EXISTS `users`";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.ConnectBD()) {
            String sql = "INSERT INTO `kata1_1_4`.`users` (`name`, `lastName`, `age`) VALUES (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.ConnectBD()) {
            String sql = "DELETE FROM `kata1_1_4`.`users` WHERE (`id` = ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = Util.ConnectBD()) {
            String sql = "SELECT * FROM kata1_1_4.users";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
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
        try (Connection con = Util.ConnectBD()) {
            String sql = "TRUNCATE `kata1_1_4`.`users`";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
