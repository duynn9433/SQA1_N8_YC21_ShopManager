package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager.model.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO extends DAO{
    public BillDAO() {
        super();
    }

    public ArrayList<Bill> searchBill(String key){
        ArrayList<Bill> result = new ArrayList<Bill>();
        String sql = "SELECT * FROM bill WHERE id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Bill bill = new Bill();
                bill.setClient(new ClientDAO().getClient(rs.getInt("clientId")));
                bill.setBuyingGoodsList((ArrayList<BuyingGoods>) new BuyingGoodsDAO().getBuyingGoods(rs.getInt("id")));
                bill.setId(rs.getInt("id"));
                bill.setPaymentTotal(rs.getLong("paymentTotal"));
                bill.setSaleOf(rs.getFloat("saleOf"));
                bill.setNote(rs.getString("note"));
                bill.setPaid(rs.getBoolean("isPaid"));
                bill.setActive(rs.getBoolean("isActive"));
                result.add(bill);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }



    public boolean editBill(Bill bill){
        boolean success = false;
        try {
            con.setAutoCommit(false);
            String sql = "UPDATE bill SET  paymentDate= ?,paymentTotal=?,saleOf=?,note=?,isPaid=?,isActive=?"
                    + " WHERE (`id` = ?);";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setTimestamp(1, Timestamp.valueOf(bill.getPaymentDate()));
            ps.setLong(2, bill.getPaymentTotal());
            ps.setFloat(3,bill.getSaleOf());
            ps.setString(4,bill.getNote());
            ps.setBoolean(5,bill.isPaid());
            ps.setBoolean(6,bill.isActive());
           // ps.setArray(7, (Array) bill.getBuyingGoodsList());

            ps.setString(7, String.valueOf(bill.getId()));
            ps.executeUpdate();
            System.out.println("Edit SQL");
            con.commit();
            con.setAutoCommit(true);
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteBill(Bill bill) throws SQLException{
        boolean success = false;
        try {
            con.setAutoCommit(false);
//            String sql = "DELETE FROM bill WHERE id = ?";
            String sql = "UPDATE bill SET isActive = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1,false);
            ps.setInt(2,bill.getId());
            //ps.setString(2, String.valueOf(client.getID()));
          //  ps.executeUpdate();
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            success = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return success;
    }
}
