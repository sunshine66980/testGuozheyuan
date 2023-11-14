package com.geekaca.demo;

import java.sql.*;

public class TestTransaction {
    private static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/testdb?useSSL=false&characterEncoding=utf8&serverTimezone=GMT";
    private static String userName = "root";
    private static String passWord = "123456";

    public static void main(String[] args) {
        transferMoney();
    }

    public static void transferMoney() {
        String sql1 = "update account set money = money * 100 where id = 10000";
        String sql2 = "update account set money = money / 100 where id = 10000";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, userName, passWord);
            //            关闭自动提交
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql1);
            pstmt.executeUpdate();
            PreparedStatement pstmt2 = connection.prepareStatement(sql2);
            pstmt2.executeUpdate();
            //            手动提交
            connection.commit();
//            手动回滚
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
