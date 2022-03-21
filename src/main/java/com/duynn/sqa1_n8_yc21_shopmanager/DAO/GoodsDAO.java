package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Goods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsDAO extends DAO{
    public GoodsDAO() {
        super();
    }

    public Goods getGoods(int id){
        Goods g = new Goods();
        String sql="SELECT FROM goods WHERE goodId=?";
        try{
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                g.setID(rs.getInt("ID"));
                g.setName(rs.getString("name"));
                g.setUnity(rs.getString("unity"));
                g.setPricePerUnit(rs.getLong("pricePerUnit"));
                g.setDescription(rs.getString("description"));
                g.setActive(rs.getBoolean("isActive"));
                ps.executeQuery();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }
}
