package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(45), lastName VARCHAR(45), age INT)";
        try(Statement statement = Util.connection().createStatement()) {
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.err.println("Такая таблица уже существует");
        }
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE `demodb`.`users`\n";
        try(Statement statement = Util.connection().createStatement()) {
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.err.println("Данной таблицы не существует");;
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Util.connection().prepareStatement("INSERT users (name, lastName, age) VALUES ('" +
                name + "', '" + lastName + "', " + age + ")");
        System.out.println("User с именем –  " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> list = null;
        try (Statement statement = Util.connection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            list = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
                // получение содержимого строк
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {
        try(Statement statement = Util.connection().createStatement()) {
            statement.executeUpdate("delete FROM users");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
