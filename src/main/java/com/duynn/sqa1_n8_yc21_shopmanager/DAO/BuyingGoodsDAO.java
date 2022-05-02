package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Goods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyingGoodsDAO extends DAO{
    public BuyingGoodsDAO() {
        super();
    }

    public ArrayList<BuyingGoods> searchBuyingGoods(String  billID){
        ArrayList<BuyingGoods> list = new ArrayList<>();
        String sql="SELECT * FROM buying_goods WHERE billId="+billID;
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setString(1,billID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               BuyingGoods b = new BuyingGoods();
               int id = rs.getInt("id"); b.setID(id);
               int amount = rs.getInt("amount"); b.setAmount(amount);
               long pricePerUnit = rs.getLong("pricePerUnit");b.setPricePerUnit(pricePerUnit);
               b.setTotalPrice(amount*pricePerUnit);
               String note = rs.getString("note");b.setNote(note);
               int goodid = rs.getInt("goodsId");
               GoodsDAO goodsDAO =  new GoodsDAO();
               Goods goods= goodsDAO.getGoods(goodid);
                b.setGoods(goods);
                list.add(b);
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean deleteBuyingGoods(int billId) throws SQLException {
        boolean success = false;

        try {
            con.setAutoCommit(false);
//            String sql = "DELETE FROM bill WHERE id = ?";
            String sql = "DELETE buying_goods WHERE id=?";
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

    public void save(BuyingGoods b, int id) {
        try {
            con.setAutoCommit(false);
            String sql = "INSERT INTO `shop_manager`.`buying_goods` (`amount`, `pricePerUnit`, `billId`, `goodsId`) " +
                    "VALUES (?, ?, ?, ?) ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,b.getAmount());
            ps.setLong(2,b.getPricePerUnit());
            ps.setInt(3,id);
            ps.setInt(4,b.getGoods().getID());

            ps.execute();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
