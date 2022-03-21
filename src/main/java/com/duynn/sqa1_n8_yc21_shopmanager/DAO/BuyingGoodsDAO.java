package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager.model.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BuyingGoodsDAO extends DAO{
    public BuyingGoodsDAO() {
        super();
    }

    public List<BuyingGoods> getBuyingGoods(int billID){
        List<BuyingGoods> list = new ArrayList<>();
        String sql="SELECT * FROM buying_goods WHERE billId=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,billID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               BuyingGoods b = new BuyingGoods();
               b.setID(rs.getInt("id"));
               b.setAmount(rs.getInt("amount"));
               b.setPricePerUnit(rs.getLong("pricePerUnit"));
               b.setTotalPrice(rs.getLong("totalPrice"));
               b.setNote(rs.getString("note"));
               b.setGoods(new GoodsDAO().getGoods(rs.getInt("goodId")));
               ps.executeQuery();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // buyinggoods.setGoods(new GoodsDAO().getGoods(rs.getInteger("goodsID"))))

        return list;
    }
    public boolean deleteBuyingGoods(int billId) throws SQLException {
        boolean success = false;

        try {
            con.setAutoCommit(false);
//            String sql = "DELETE FROM bill WHERE id = ?";
            String sql = "DELETE buying_goods WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,billId);
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            success = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return success;
    }
    public void inSert(List<BuyingGoods> list){
        //String sql="";
        try {
            con.commit();
            con.setAutoCommit(false);
//            String sql = "DELETE FROM bill WHERE id = ?";

            for (int i = 0; i < list.size(); i++) {
                String sql = "INSERT INTO buying_goods WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                //ps.setInt(1,list);
                ps.executeUpdate();
            }
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
