package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.BuyingGoods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyingGoodsDAO extends DAO{
    public BuyingGoodsDAO() {
        super();
    }

    public List<BuyingGoods> searchBuyingGoods(int billID){
        ArrayList<BuyingGoods> list = new ArrayList<>();
        String sql="SELECT * FROM buying_goods WHERE billId like?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,billID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               BuyingGoods b = new BuyingGoods();
               b.setID(rs.getInt("id"));
               b.setAmount(rs.getInt("amount"));
               b.setPricePerUnit(rs.getLong("pricePerUnit"));
               b.setTotalPrice(b.getAmount()*b.getPricePerUnit());
               b.setNote(rs.getString("note"));
               b.setGoods(new GoodsDAO().getGoods(rs.getInt("id")));
               ps.executeQuery();
            }
            ps.close();
            rs.close();
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
