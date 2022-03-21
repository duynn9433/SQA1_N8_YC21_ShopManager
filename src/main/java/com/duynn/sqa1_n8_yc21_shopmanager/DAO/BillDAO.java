package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO extends DAO{
    public BillDAO() {
        super();
    }

    public ArrayList<Bill> searchBill(String key){
        ArrayList<Bill> result = new ArrayList<Bill>();
        String sql = "SELECT * FROM bill WHERE id LIKE ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setPaymentDate(rs.getDate("paymentDate"));
                bill.setPaymentTotal(rs.getLong("paymentTotal"));
                bill.setPaymentType(rs.getString("paymentType"));
                bill.setSaleOf(rs.getFloat("saleOf"));
                bill.setNote(rs.getString("note"));
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
            String sql = "UPDATE bill SET  paymentDate= ?,paymentTotal=?,paymentType=?,saleOf=?,note=?"
                    + " WHERE (`id` = ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, (Date) bill.getPaymentDate());
            ps.setLong(2, bill.getPaymentTotal());
            ps.setString(3, bill.getPaymentType());
            ps.setFloat(4,bill.getSaleOf());
            ps.setString(5,bill.getNote());
            ps.setString(6, String.valueOf(bill.getId()));
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
            String sql = "DELETE FROM bill WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,bill.getId());
            //ps.setString(2, String.valueOf(client.getID()));
          //  ps.executeUpdate();
            ps.executeQuery();
            con.commit();
            con.setAutoCommit(true);
            success = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return success;
    }
}
