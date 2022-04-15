package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Goods;
import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO extends DAO{
    public GoodsDAO() {
        super();
    }

    public Goods getGoods(int id){
        Goods g = new Goods();
        String sql="SELECT * FROM goods WHERE id=?";
        try{
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                if(rs.getBoolean("isActive")){
                    g.setID(rs.getInt("id"));
                    g.setName(rs.getString("name"));
                    g.setUnity(rs.getString("unity"));
                    g.setPricePerUnit(rs.getLong("pricePerUnit"));
                    g.setDescription(rs.getString("description"));
                    g.setActive(rs.getBoolean("isActive"));
                }
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }

    public List<Goods> searchByName(String name){
        List<Goods> list = new ArrayList<>();
        String sql="SELECT * FROM goods g WHERE g.name LIKE ?";
        try{
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1, "%" +name+ "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Goods g = new Goods();
                g.setID(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setUnity(rs.getString("unity"));
                g.setPricePerUnit(rs.getLong("pricePerUnit"));
                g.setDescription(rs.getString("description"));
                g.setActive(rs.getBoolean("isActive"));
                if(g.isActive())
                    list.add(g);
            }
            MyLogger.logInfo(GoodsDAO.class, list.toString());
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            MyLogger.logError(GoodsDAO.class, e.getMessage(),e);
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        List<Goods> list = new GoodsDAO().searchByName("bi");
        System.out.println("printttttttttttttttt");
        for(Goods g : list){
            System.out.println(g);
        }
//        System.out.println(new GoodsDAO().getGoods(2));
    }
}
