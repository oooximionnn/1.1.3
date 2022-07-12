package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        User user1 = new User("Viktor", "Petrov", (byte) 23);
        User user2 = new User("Maria", "Sidorova", (byte) 55);
        User user3 = new User("Elena", "Nikolaevna", (byte) 31);
        User user4 = new User("Nikita", "Andreev", (byte) 19);

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
