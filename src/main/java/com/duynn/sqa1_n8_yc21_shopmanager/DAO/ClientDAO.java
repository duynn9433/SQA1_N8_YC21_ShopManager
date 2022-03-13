package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;

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
    }

    public List<Client> searchClient(String sdt) throws SQLException {
        List<Client> res = new ArrayList<>();
        String sql = "select * from client c where c.phoneNumber = ?";
        PreparedStatement ps;

        ps = con.prepareStatement(sql);
        ps.setString(1, sdt);
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
        return res;
    }

        public void editClient (Client client) throws SQLException {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE client SET name = ?,address=?,phoneNumber=?" +
                    " WHERE (`id` = ?);");
            ps.setString(1, client.getName());
            ps.setString(2, client.getAddress());
            ps.setString(3, client.getPhoneNumber());
            ps.setString(4, String.valueOf(client.getID()));
            ps.executeQuery();

            con.commit();
            con.setAutoCommit(true);
        }
    }
