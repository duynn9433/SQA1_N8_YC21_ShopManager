package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyTestWatcher.class)
class BillDAOTest extends MyTestWatcher {
    private BillDAO billDAO;
    @BeforeEach
    void setUp() {
        super.setLogger(BillDAO.class);
        billDAO = new BillDAO();
    }

    @Test
    void searchBill() {
    }

    @Test
    void allBill() {
    }

    @Test
    void payment() {
    }

    @Test
    void editBill() {
    }

    @Test
    void deleteBill() {
    }

    @Test
    void save() {
    }
}