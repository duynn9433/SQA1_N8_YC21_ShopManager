package com.duynn.sqa1_n8_yc21_shopmanager.DAO;
import com.duynn.sqa1_n8_yc21_shopmanager.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    protected static Connection con;
     
    public DAO(){
        if(con == null){
            String dbUrl = 
                "jdbc:mysql://localhost:3306/testdb?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection (dbUrl, "root", "0338103714Hoanh");
            }catch(ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        }
    }
    public static void main(String[] args) throws SQLException {
        UserDAO ud = new UserDAO();
        User user = new User();
        ud.checkLogin(user);
        DAO d = new DAO();
        System.out.println("dao:"+d);
        System.out.println("con:"+d.con);
    }
}