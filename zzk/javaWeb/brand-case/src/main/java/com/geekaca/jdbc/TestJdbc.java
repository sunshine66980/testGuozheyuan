package com.geekaca.jdbc;

import java.sql.*;
import java.util.Collection;

public class TestJdbc {
    public static void main(String[] args) throws ClassNotFoundException {
        testSelect();
    }

    private static void testSelect() throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false&characterEncodeing=utf8";
        String userName = "root";
        String passWord = "123456";
//        Class.forName("com.mysql.Driver");

        try {
            Connection connection = DriverManager.getConnection(url, userName, passWord);
            Statement statement = connection.createStatement();
            String sql = "select * from account";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double money = resultSet.getDouble("money");
                System.out.println(id + "," + name + "," + money);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
