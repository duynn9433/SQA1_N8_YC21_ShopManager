package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyTestWatcher.class)
class GoodsDAOTest extends MyTestWatcher{
    private GoodsDAO goodsDAO;
    @BeforeEach
    void setUp() {
        super.setLogger(GoodsDAO.class);
        goodsDAO = new GoodsDAO();
    }

    @Test
    void getGoods() {
    }

    @Test
    void searchByName() {
    }

    @Test
    void main() {
    }
}