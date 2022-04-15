package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
    void searchClient() {
    }

    @Test
    void editClient() {
    }

    @Test
    void deleteClient() {
    }
}