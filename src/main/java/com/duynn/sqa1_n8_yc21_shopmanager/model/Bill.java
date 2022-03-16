package com.duynn.sqa1_n8_yc21_shopmanager.model;

public class Bill {

    private int id;
    private String paymentDate;
    private long paymentTotal;
    private String paymentType;
    private float saleOf;
    private String note;

    public Bill() {
    }

    public Bill(int id, String paymentDate, long paymentTotal, String paymentType, float saleOf, String note) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentTotal = paymentTotal;
        this.paymentType = paymentType;
        this.saleOf = saleOf;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
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

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", paymentDate='" + paymentDate + '\'' +
                ", paymentTotal=" + paymentTotal +
                ", paymentType='" + paymentType + '\'' +
                ", saleOf=" + saleOf +
                ", note='" + note + '\'' +
                '}';
    }
}
