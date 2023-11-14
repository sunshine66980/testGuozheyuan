package com.geekaca.demo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class TestPool {
    public static void main(String[] args) throws Exception {
        //    加载配置
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/java/com/geekaca/properties/druid.properties"));


//        DataSource是java官方规范，DruidDataSourceFactory是厂商自己实现的
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        Connection connection = dataSource.getConnection();

        connection.close();
        System.out.println("连接归还");
    }

}
