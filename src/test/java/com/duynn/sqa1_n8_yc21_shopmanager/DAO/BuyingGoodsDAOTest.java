package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
    }

    @Test
    void deleteBuyingGoods() {
    }

    @Test
    void inSert() {
    }

    @Test
    void save() {
    }
}