package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager.model.BuyingGoods;

import java.sql.*;
import java.util.ArrayList;

public class BillDAO extends DAO{
    public BillDAO() {
        super();
    }

    public ArrayList<Bill> searchBill(String key){
        ArrayList<Bill> result = new ArrayList<>();
        String sql = "SELECT * FROM bill b WHERE b.id LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Bill bill = new Bill();
                bill.setClient(new ClientDAO().getClient(rs.getInt("id")));
//                bill.setBuyingGoodsList((ArrayList<BuyingGoods>) new BuyingGoodsDAO().getBuyingGoods(rs.getInt("id")));
                bill.setUser(new UserDAO().getUser(rs.getInt("id")));
                bill.setId(rs.getInt("id"));
                bill.setPaymentDate(rs.getTimestamp("paymentDate").toLocalDateTime());
//                bill.setPaymentTotal(rs.getLong("paymentTotal")); dẫn xuất tự tính
//                setpaymentTotal
//                long payment = 0;
//                for (int i =0 ;i<bill.getBuyingGoodsList().size();i++){
//                    BuyingGoods buyingGoods=bill.getBuyingGoodsList().get(i);
//                    payment = payment + buyingGoods.getTotalPrice();
//                }
//                bill.setPaymentTotal(payment);
                bill.setSaleOff(rs.getFloat("saleOff"));
                bill.setNote(rs.getString("note"));
                bill.setPaid(rs.getBoolean("isPaid"));
                bill.setActive(rs.getBoolean("isActive"));
                if(bill.isActive()) result.add(bill);
            }
            ps.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<Bill> allBill(){
        ArrayList<Bill> result = new ArrayList<>();
        String sql = "SELECT * FROM bill ";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setString(1);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Bill bill = new Bill();
                bill.setClient(new ClientDAO().getClient(rs.getInt("id")));
//                bill.setBuyingGoodsList((ArrayList<BuyingGoods>) new BuyingGoodsDAO().getBuyingGoods(rs.getInt("id")));
                bill.setUser(new UserDAO().getUser(rs.getInt("id")));
                bill.setId(rs.getInt("id"));
                bill.setPaymentDate(rs.getTimestamp("paymentDate").toLocalDateTime());
//                bill.setPaymentTotal(rs.getLong("paymentTotal")); dẫn xuất tự tính
//                setpaymentTotal
//                long payment = 0;
//                for (int i =0 ;i<bill.getBuyingGoodsList().size();i++){
//                    BuyingGoods buyingGoods=bill.getBuyingGoodsList().get(i);
//                    payment = payment + buyingGoods.getTotalPrice();
//                }
//                bill.setPaymentTotal(payment);
                bill.setSaleOff(rs.getFloat("saleOff"));
                bill.setNote(rs.getString("note"));
                bill.setPaid(rs.getBoolean("isPaid"));
                bill.setActive(rs.getBoolean("isActive"));
                if(bill.isActive()) result.add(bill);
            }
            ps.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }




    public boolean editBill(Bill bill){
        boolean success = false;
        try {
            con.setAutoCommit(false);
            String sql = "UPDATE bill SET paymentDate= ?,saleOff=?,note=? WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setTimestamp(1, Timestamp.valueOf(bill.getPaymentDate()));
            ps.setFloat(2,bill.getSaleOff());
            ps.setString(3,bill.getNote());
            ps.setString(4, String.valueOf(bill.getId()));
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

    public boolean deleteBill(String id) throws SQLException{
        boolean success = false;
        try {
            con.setAutoCommit(false);
            String sql = "UPDATE bill SET isActive = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"0");
            ps.setString(2, id);
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

    public void save(Bill bill) {
        try {
            //manual get id
            int billID;
            PreparedStatement psinit;
            ResultSet rsinit;

            psinit = con.prepareStatement("select max(c.id) as max from bill c");
            rsinit = psinit.executeQuery();
            rsinit.next();
            billID = rsinit.getInt("max");

            bill.setId(++billID);

            psinit.close();
            rsinit.close();

            con.setAutoCommit(false);
            String sql = "INSERT INTO bill (id, paymentDate, saleOff, isPaid, isActive, userId, clientId) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,bill.getId());
            ps.setTimestamp(2,Timestamp.valueOf(bill.getPaymentDate()));
            ps.setFloat(3,bill.getSaleOff());
            ps.setBoolean(4,bill.isPaid());
            ps.setBoolean(5,bill.isActive());
            ps.setInt(6,bill.getUser().getID());
            ps.setInt(7,bill.getClient().getID());
            BuyingGoodsDAO buyingGoodsDAO = new BuyingGoodsDAO();
            ps.execute();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            for(BuyingGoods b:bill.getBuyingGoodsList()){
                buyingGoodsDAO.save(b,bill.getId());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
