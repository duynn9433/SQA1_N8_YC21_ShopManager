package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager.model.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;
import java.util.ArrayList;

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
        ArrayList<Bill> bill = billDAO.searchBill("1");
        assertEquals(1,bill.size());
    }

    @Test
    void allBill() {
        ArrayList<Bill> bill = billDAO.allBill();
        assertEquals(2,bill.size());
    }

    @Test
    void payment() {
        Bill bill = new Bill(2,30000,2,"asdsfs",true,true);
        long bill1= billDAO.payment(2);
        assertEquals(30000,bill1);
    }

    @Test
    void editBill() {
        ArrayList<Bill> list = new ArrayList<>();
        Bill bill1 = new Bill(4,30000,3,"asdsfs",true,true);
        list.add(bill1);
        Bill bill = new Bill(4,30000,2,"asdsfs",true,true);
        boolean edt = billDAO.editBill(bill);
        assertEquals(true,edt);
    }

    @Test
    void deleteBill() throws SQLException {
        ArrayList<Bill> bills = billDAO.searchBill("2");
        boolean delete = billDAO.deleteBill("2");
        assertEquals(true,delete);
    }

    @Test
    void save() {

    }
}