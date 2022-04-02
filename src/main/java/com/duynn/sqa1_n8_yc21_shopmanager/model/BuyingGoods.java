package com.duynn.sqa1_n8_yc21_shopmanager.model;

import java.io.Serializable;

public class BuyingGoods implements Serializable {
    private int ID;
    private int amount;
    private long pricePerUnit;
    private long totalPrice;
    private String note;
    private Goods goods;

    public BuyingGoods() {
    }

    public  void reCalTotalPrice() {
        this.totalPrice = this.amount * this.pricePerUnit;
    }

    public BuyingGoods(int ID, int amount, long pricePerUnit, long totalPrice, String note, Goods goods) {
        this.ID = ID;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
        this.note = note;
        this.goods = goods;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(long pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
