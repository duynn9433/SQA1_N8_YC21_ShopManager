package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.Goods;
import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

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
        List<Goods> list = goodsDAO.searchByName("bi");
        assertEquals(2, list.size());
        //no record
        list = goodsDAO.searchByName("0");
        assertEquals(0, list.size());
        //active false
        list = goodsDAO.searchByName("1");
        Goods goods = new Goods(5,"11","11",11,"test active T",true);
        assertEquals(1, list.size());
        assertEquals(goods, list.get(0));
        //active true
        list = goodsDAO.searchByName("1");
        goods = new Goods(5,"11","11",11,"test active T",true);
        assertEquals(1, list.size());
        assertEquals(goods, list.get(0));
    }
    @Test
    void searchByNameNoRecord() {
        List<Goods> list = goodsDAO.searchByName("0");
        assertEquals(0, list.size());
    }
    @Test
    void searchByNameActiveFalse() {
        List<Goods> list = goodsDAO.searchByName("1");
        Goods goods = new Goods(5,"11","11",11,"test active T",true);
        assertEquals(1, list.size());
        assertEquals(goods, list.get(0));
    }
    @Test
    void searchByNameActiveTrue() {
        List<Goods> list = goodsDAO.searchByName("1");
        Goods goods = new Goods(5,"11","11",11,"test active T",true);
        assertEquals(1, list.size());
        assertEquals(goods, list.get(0));
    }


    @Test
    void main() {
    }
}