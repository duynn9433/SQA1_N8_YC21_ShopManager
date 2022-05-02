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
class BuyingGoodsDAOTest extends MyTestWatcher {
    private BuyingGoodsDAO buyingGoodsDAO;
    @BeforeEach
    void setUp() {
        super.setLogger(BuyingGoodsDAO.class);
        buyingGoodsDAO = new BuyingGoodsDAO();
    }

    @Test
    void searchBuyingGoods() {
        ArrayList<BuyingGoods> buyingGoods = buyingGoodsDAO.searchBuyingGoods("1");
        assertEquals(1,buyingGoods.size());

    }

    @Test
    void deleteBuyingGoods() throws SQLException {
        // ko Test delete dc
        boolean delete = buyingGoodsDAO.deleteBuyingGoods(1);
        assertEquals(false,delete);

    }

    @Test
    void inSert() {
        // không dùng
    }

    @Test
    void save() {
    }
}