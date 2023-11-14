package com.geekaca.demo;


import java.sql.*;

public class TestLogin {
    private static String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/testdb?useSSL=false&characterEncoding=utf8&serverTimezone=GMT";
    private static String userName = "root";
    private static String passWord = "123456";
    public static void main(String[] args) {
        login("大番薯","123456");

    }

    private static boolean login(String uname,String upass){
        Boolean isLoginOK = false;
        String sql = "SELECT count(*) as cnt FROM t_user WHERE user_name = ? and password = ?";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl,userName,passWord);
//            会被sql注入攻击
//            Statement statement = connection.createStatement(sql);
//            预编译防止sql注入
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,uname);
            pstmt.setString(2,upass);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                int cnt = resultSet.getInt("cnt");
                if (cnt > 0){
                    System.out.println("登录成功");
                    isLoginOK = true;
                }else {
                    isLoginOK = false;
                }
            }
            isLoginOK = false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isLoginOK;
    }

}
