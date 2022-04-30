package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager.model.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                bill.setClient(new ClientDAO().getClient(rs.getInt("ClientId")));
                bill.setUser(new UserDAO().getUser(rs.getInt("userId")));
                bill.setId(rs.getInt("id"));
                bill.setPaymentDate(rs.getTimestamp("paymentDate").toLocalDateTime());
                //long payment = payment(bill.getId())*bill.getSaleOff();
                float payment =payment(bill.getId()) - payment(bill.getId()) *bill.getSaleOff();
                long a =  (long) payment;
                bill.setPaymentTotal(a);
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
    public Bill searchBillid(String key){
        Bill bill = new Bill();
        String sql = "SELECT * FROM bill b WHERE b.id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){


                int id_client = rs.getInt("clientID");
                ClientDAO client = new ClientDAO();
                Client c = client.seachClientID(String.valueOf(id_client));
                bill.setClient(c);
                bill.setUser(new UserDAO().getUser(rs.getInt("userId")));
                bill.setId(rs.getInt("id"));
                bill.setPaymentDate(rs.getTimestamp("paymentDate").toLocalDateTime());
                //long payment = payment(bill.getId())*bill.getSaleOff();
                float payment =payment(bill.getId()) - payment(bill.getId()) *bill.getSaleOff();
                long a =  (long) payment;
                bill.setPaymentTotal(a);
                bill.setSaleOff(rs.getFloat("saleOff"));
                bill.setNote(rs.getString("note"));
                bill.setPaid(rs.getBoolean("isPaid"));
                bill.setActive(rs.getBoolean("isActive"));
                //buyinggoodslist
                BuyingGoodsDAO buyingGoodsDAO =  new BuyingGoodsDAO();
                ArrayList<BuyingGoods> buyingGoods =  buyingGoodsDAO.searchBuyingGoods(key);
                bill.setBuyingGoodsList(buyingGoods);

            }
            ps.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        if(bill.isActive()) return bill; else return null;
    }
    public ArrayList<Bill> allBill(){
        ArrayList<Bill> result = new ArrayList<>();
        String sql = "SELECT * FROM bill ";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Bill bill = new Bill();
                bill.setClient(new ClientDAO().getClient(rs.getInt("id")));
                bill.setUser(new UserDAO().getUser(rs.getInt("id")));
                bill.setId(rs.getInt("id"));
                bill.setPaymentDate(rs.getTimestamp("paymentDate").toLocalDateTime());
                bill.setSaleOff(rs.getFloat("saleOff"));
                float payment =payment(bill.getId()) - payment(bill.getId()) *bill.getSaleOff();
                long a =  (long) payment;
                bill.setPaymentTotal(a);
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
    
    
    public long payment (int id){
        long paymenttotal = 0;
        String sql="SELECT * FROM buying_goods WHERE billId=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BuyingGoods b = new BuyingGoods();
                int a = rs.getInt("amount");
                long c = rs.getLong("pricePerUnit");
                paymenttotal= paymenttotal+ a*c;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymenttotal;

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
