package com.duynn.sqa1_n8_yc21_shopmanager.model;

import javafx.beans.DefaultProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


public class Client implements Serializable {
    private static final long serialVersionUID=1L;

    private int ID;
    private String name;
    private String address;
    private String phoneNumber;
    private boolean isActive;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NotEmpty
    @Length(min=3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotEmpty
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
