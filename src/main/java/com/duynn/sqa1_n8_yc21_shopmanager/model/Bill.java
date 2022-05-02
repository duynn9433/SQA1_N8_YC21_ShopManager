package com.duynn.sqa1_n8_yc21_shopmanager.model;

import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyException;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Bill implements Serializable {

    private int id;
    private LocalDateTime paymentDate;
    private long paymentTotal;
    private float saleOff;
    private String note;
    private boolean isPaid;
    private boolean isActive;
    private User user;
    private Client client;
    private ArrayList<BuyingGoods> buyingGoodsList;

    public void reCalPaymentTotal() {
        paymentTotal =0;
        for (BuyingGoods buyingGoods:buyingGoodsList) {
            buyingGoods.reCalTotalPrice();
            paymentTotal+=buyingGoods.getTotalPrice();
        }
    }
    public void addBuyingGoods(BuyingGoods buyingGoods) throws MyException {
        for (int i =0 ;i<buyingGoodsList.size();i++) {
            BuyingGoods b =buyingGoodsList.get(i);
            if(b.getGoods().getName().equals(buyingGoods.getGoods().getName())){
                long temp = (long)b.getAmount()+buyingGoods.getAmount();
                if(temp> Integer.MAX_VALUE){
                    throw new MyException("Số lượng quá lớn");
                }
                b.setAmount(b.getAmount()+buyingGoods.getAmount());
                b.setTotalPrice(b.getPricePerUnit()*b.getAmount());
                return;
            }
        }
        buyingGoodsList.add(buyingGoods);
    }
    public Bill(int id, long paymentTotal, float saleOff, String note, boolean isPaid, boolean isActive) {
        this.id = id;
//        this.paymentDate = paymentDate;
        this.paymentTotal = paymentTotal;
        this.saleOff = saleOff;
        this.note = note;
        this.isPaid = isPaid;
        this.isActive = isActive;
    }

    public Bill(int id, LocalDateTime paymentDate, long paymentTotal, float saleOff, String note, boolean isPaid, boolean isActive) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentTotal = paymentTotal;
        this.saleOff = saleOff;
        this.note = note;
        this.isPaid = isPaid;
        this.isActive = isActive;
    }

    public Bill() {
        buyingGoodsList = new ArrayList<>();
        isActive = true;
        isPaid = false;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", paymentTotal=" + paymentTotal +
                ", saleOf=" + saleOff +
                ", note='" + note + '\'' +
                ", isPaid=" + isPaid +
                ", isActive=" + isActive +
                ", user=" + user +
                ", client=" + client +
                ", buyingGoodsList=" + buyingGoodsList +
                '}';
    }

    public Bill(int id, LocalDateTime paymentDate, long paymentTotal, float saleOff, String note, boolean isPaid, boolean isActive, User user, Client client, ArrayList<BuyingGoods> buyingGoodsList) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentTotal = paymentTotal;
        this.saleOff = saleOff;
        this.note = note;
        this.isPaid = isPaid;
        this.isActive = isActive;
        this.user = user;
        this.client = client;
        this.buyingGoodsList = buyingGoodsList;
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

    public float getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(float saleOf) {
        this.saleOff = saleOf;
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

    public ArrayList<BuyingGoods> getBuyingGoodsList() {
        return buyingGoodsList;
    }

    public void setBuyingGoodsList(ArrayList<BuyingGoods> buyingGoodsList) {
        this.buyingGoodsList = buyingGoodsList;
    }

//    public void setPaymentDate(Timestamp paymentDate) {
//           final LocalDateTime paymentDate1 = this.paymentDate;
////           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
////           paymentDate =Timestamp.valueOf(paymentDate1);
//             Timestamp paymentDate11  = Timestamp.valueOf(paymentDate1.toString()) ;
//    }


//    public void setPaymentDate(LocalDateTime paymentDate) {
//        this.paymentDate = paymentDate;
//    }

}
