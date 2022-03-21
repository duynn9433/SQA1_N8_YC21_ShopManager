package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.model.BuyingGoods;

import java.util.ArrayList;
import java.util.List;

public class BuyingGoodsDAO extends DAO{
    public BuyingGoodsDAO() {
        super();
    }

    public List<BuyingGoods> getBuyingGoods(int billID){
        List<BuyingGoods> list = new ArrayList<>();

        // buyinggoods.setGoods(new GoodsDAO().getGoods(rs.getInteger("goodsID"))))

        return list;
    }

}
