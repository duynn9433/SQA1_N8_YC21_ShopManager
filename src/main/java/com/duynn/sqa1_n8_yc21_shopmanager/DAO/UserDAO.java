package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO {

    public UserDAO() {
        super();
    }

    /**
     * @author nguyen ngoc duy
     */
    public boolean checkLogin(User user) throws SQLException {
        boolean result = false;
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        System.out.println("pscon:"+con);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getBoolean("isActive")) {
                user.setID(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPosition(rs.getString("position"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setIsActive(true);
                result = true;
            }

        }
        ps.close();
        rs.close();
        return result;
    }

    public List<User> getAllStaff(LocalDateTime startDate, LocalDateTime endDate) {
        List<User> res = new ArrayList<>();
        String sql = "{call getFreeStaff(?,?)}";
        try {
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dtf.format(startDate));
            ps.setString(2, dtf.format(endDate));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setID(rs.getInt("ID"));
                u.setIsActive(rs.getBoolean("isActive"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setPhoneNumber(rs.getString("phoneNumber"));
                u.setPosition(rs.getString("position"));
                u.setUsername(rs.getString("username"));
                res.add(u);
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
