package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;
import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends DAO {
    public ClientDAO() {
        super();
    }

    public void addClient(Client client) throws SQLException {
        con.setAutoCommit(false);

//        //manual get id
//        int clientID;
//        PreparedStatement psinit;
//        ResultSet rsinit;
//
//        psinit = con.prepareStatement("select max(c.id) as max from client c");
//        rsinit = psinit.executeQuery();
//        rsinit.next();
//        clientID = rsinit.getInt("max");
//
//        client.setID(++clientID);
//
//        psinit.close();
//        rsinit.close();
        //add client
        PreparedStatement ps = con.prepareStatement("insert into client(name,phoneNumber,address) values (?,?,?)");
        ps.setString(1, client.getName());
        ps.setString(2, client.getPhoneNumber());
        ps.setString(3, client.getAddress());

        ps.executeUpdate();

        con.commit();
        con.setAutoCommit(true);
        ps.close();
    }

    public Client getClient(int clientID){
        return new Client();
    }

    public static void main(String[] args) throws SQLException {
        for (Client c:new ClientDAO().searchClient("0001") ){
            System.out.println(c);
        };
    }
    public Client seachClientID(String id){
        Client c = new Client();
        String sql = "select * from client where id = " + id;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                c.setID(rs.getInt("ID"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setPhoneNumber(rs.getString("phoneNumber"));
                c.setActive(true);

            }
        } catch (SQLException e) {

        }
        return  c;
    }
    public List<Client> searchClient(String sdt) throws SQLException {
        List<Client> res = new ArrayList<>();
        String sql = "select * from client c where c.phoneNumber LIKE ?";
        PreparedStatement ps;

        ps = con.prepareStatement(sql);
        ps.setString(1, "%" +sdt+ "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getBoolean("isActive")) {
                Client c = new Client();
                c.setID(rs.getInt("ID"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setPhoneNumber(rs.getString("phoneNumber"));
                c.setActive(true);
                res.add(c);
            }
        }
        ps.close();
        rs.close();
        MyLogger.logInfo(ClientDAO.class, res.toString());
        return res;
    }

    public boolean editClient(Client client){
        boolean success = false;
        try {
            con.setAutoCommit(false);
            String sql = "UPDATE client SET name = ?,address=?,phoneNumber=?"
                    + " WHERE (`id` = ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getAddress());
            ps.setString(3, client.getPhoneNumber());
            ps.setString(4, String.valueOf(client.getID()));
            ps.executeUpdate();
//            System.out.println("Edit SQL");
            con.commit();
            con.setAutoCommit(true);
            success = true;
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteClient(String id) throws SQLException{
        boolean success = false;
        try {
            con.setAutoCommit(false);
            String sql = "UPDATE client SET isActive = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"0");
            ps.setString(2,id);
            ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            success = true;
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return success;
    }

}
