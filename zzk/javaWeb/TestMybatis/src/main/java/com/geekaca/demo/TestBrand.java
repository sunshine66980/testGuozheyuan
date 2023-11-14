package com.geekaca.demo;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.geekaca.pojo.Brand;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class TestBrand {

    private static DataSource dataSource;

    public static void init() throws Exception {

        if (dataSource == null){
            //    加载配置
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/java/com/geekaca/properties/druid.properties"));
//        DataSource是java官方规范，DruidDataSourceFactory是厂商自己实现的
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        }

    }

    private static Connection getConnection() throws Exception {
        init();
        Connection connection = dataSource.getConnection();
        return connection;
//        connection.close();
    }

    public static void main(String[] args) throws Exception {

//        testAdd();
//        testDelete();
//        testUpdate();
        testSelect(1);
    }

    private static void testAdd() throws Exception {
        Connection connection = getConnection();
        String sql = "insert into tb_brand values(?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        Brand brand = new Brand();
        brand.setId(1000000);
        brand.setBrandName("大番薯");
        brand.setCompanyName("大番薯");
        brand.setStatus(1);
        brand.setDescription("大番薯是大番薯");
        brand.setOrdered(1);
        pstmt.setInt(1, brand.getId());
        pstmt.setString(2, brand.getBrandName());
        pstmt.setString(3, brand.getCompanyName());
        pstmt.setInt(4, brand.getOrdered());
        pstmt.setString(5, brand.getDescription());
        pstmt.setInt(6, brand.getStatus());
        int i = pstmt.executeUpdate();
        if (i == 1) {
            System.out.println("新增成功");

        } else {
            System.out.println("新增失败");

        }
        connection.close();

    }

    private static void testUpdate() throws Exception {
        Connection connection = getConnection();
        String sql = "Update tb_brand set brand_name=?, company_name=? where id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        Brand brand = new Brand();
        brand.setId(1000000);
        brand.setBrandName("大番薯2");
        brand.setCompanyName("大番薯2");
        brand.setStatus(1);
        brand.setDescription("大番薯是大番薯");
        brand.setOrdered(1);
        pstmt.setInt(1, brand.getId());
        pstmt.setString(2, brand.getBrandName());
        int i = pstmt.executeUpdate();
        if (i == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
        connection.close();

    }

    public static void testDelete() throws Exception {
        Connection connection = getConnection();
        String sql = "delete from tb_brand where id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        Brand brand = new Brand();
        brand.setId(1000000);
        pstmt.setInt(1, brand.getId());
        int i = pstmt.executeUpdate();
        if (i == 1) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");

        }
        connection.close();

    }
 public static Brand testSelect(int id) throws Exception {
        Connection connection = getConnection();
        String sql = "select * from tb_brand where id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        Brand brand = new Brand();
        pstmt.setInt(1, id);
        ResultSet rs  = pstmt.executeQuery();
       while (rs.next()){
           brand.setOrdered(rs.getInt("ordered"));
           brand.setBrandName(rs.getString("brand_name"));
           brand.setCompanyName(rs.getString("company_name"));
           brand.setDescription(rs.getString("description"));
           brand.setStatus(rs.getInt("status"));
           brand.setId(rs.getInt("id"));
       }
     System.out.println(brand);
     connection.close();

     return brand;

    }

}
