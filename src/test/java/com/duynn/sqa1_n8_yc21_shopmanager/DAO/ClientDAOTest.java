package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Client;
import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyTestWatcher.class)
class ClientDAOTest extends MyTestWatcher {
    private ClientDAO clientDAO;

    @BeforeEach
    void setUp() {
        super.setLogger(ClientDAO.class);
        clientDAO = new ClientDAO();
    }

    @Test
    void addClient() {
    }

    @Test
    void getClient() {
    }

    @Test
    void main() {
    }

    @Test
    void searchClient() throws SQLException {
        //no record
        List<Client> list = clientDAO.searchClient("0123456789");
        assertEquals(0, list.size());
        //active false
        list = clientDAO.searchClient("1111111111");
        assertEquals(0, list.size());
        //active true
        list = clientDAO.searchClient("2222222222");
        assertEquals(1, list.size());
        Client client = new Client(2,"2","active true","2222222222",true);
        assertEquals(client, list.get(0));
    }
    void searchClientNoRecord() throws SQLException {
        //no record
        List<Client> list = clientDAO.searchClient("0123456789");
        assertEquals(0, list.size());
    }
    void searchClientActiveFalse() throws SQLException {
        //active false
        List<Client> list = clientDAO.searchClient("1111111111");
        assertEquals(0, list.size());
    }
    void searchClientActiveTrue() throws SQLException {
        //active true
        List<Client> list = clientDAO.searchClient("2222222222");
        assertEquals(1, list.size());
        Client client = new Client(2,"2","active true","2222222222",true);
        assertEquals(client, list.get(0));
    }

    @Test
    void editClient() {
    }

    @Test
    void deleteClient() {
    }
}