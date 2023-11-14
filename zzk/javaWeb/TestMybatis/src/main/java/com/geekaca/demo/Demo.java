package com.geekaca.demo;

import com.geekaca.pojo.Account;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Demo {
    private static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/testdb?useSSL=false&characterEncoding=utf8&serverTimezone=GMT";
    private static String userName = "root";
    private static String passWord = "123456";

    public static void main(String[] args) {
        selectAccount();
    }

    public static void selectAccount() {
        String sql = "select * from account";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, userName, passWord);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs =  pstmt.executeQuery();
            List<Account> accountList = new LinkedList<>();
            while (rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                account.setMoney(rs.getDouble("money"));
                accountList.add(account);
            }
            for (Account account:accountList){
                System.out.println(account);
            }

//            手动回滚
//            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
