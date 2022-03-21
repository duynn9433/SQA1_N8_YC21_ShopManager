package com.duynn.sqa1_n8_yc21_shopmanager.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Bill {

    private int id;
    private LocalDateTime paymentDate;
    private long paymentTotal;
    private String paymentType;
    private float saleOf;
    private String note;
    private boolean isPaid;
    private boolean isActive;
    private User user;
    private Client client;
    private ArrayList<BuyingGoods> buyingGoodsList;

    public Bill() {

    }

    public Bill(int id, LocalDateTime paymentDate, long paymentTotal, String paymentType, float saleOf, String note, boolean isPaid,
                boolean isActive, User user, Client client, ArrayList<BuyingGoods> buyingGoods) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentTotal = paymentTotal;
        this.paymentType = paymentType;
        this.saleOf = saleOf;
        this.note = note;
        this.isPaid = isPaid;
        this.isActive = isActive;
        this.user = user;
        this.client = client;
        this.buyingGoodsList = buyingGoods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public long getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(long paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public float getSaleOf() {
        return saleOf;
    }

    public void setSaleOf(float saleOf) {
        this.saleOf = saleOf;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<BuyingGoods> getBuyingGoods() {
        return buyingGoodsList;
    }

    public void setBuyingGoods(ArrayList<BuyingGoods> buyingGoods) {
        this.buyingGoodsList = buyingGoods;
    }
}
