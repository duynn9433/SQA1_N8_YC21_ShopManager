package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO extends DAO{
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
        ps.setString(1,client.getName());
        ps.setString(2,client.getPhoneNumber());
        ps.setString(3,client.getAddress());

        ps.executeUpdate();

        con.commit();
        con.setAutoCommit(true);
    }
}
