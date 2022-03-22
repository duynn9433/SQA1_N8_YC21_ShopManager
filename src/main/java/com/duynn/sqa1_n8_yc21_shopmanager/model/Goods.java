package com.duynn.sqa1_n8_yc21_shopmanager.model;

import java.io.Serializable;

public class Goods implements Serializable {
    private int ID;
    private String name;
    private String unity;
    private long pricePerUnit;
    private String description;
    private boolean isActive;

    public Goods(int ID, String name, String unity, long pricePerUnit, String description, boolean isActive) {
        this.ID = ID;
        this.name = name;
        this.unity = unity;
        this.pricePerUnit = pricePerUnit;
        this.description = description;
        this.isActive = isActive;
    }

    public Goods() {
    }

    @Override
    public String toString() {
        return "Goods{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", unity='" + unity + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public long getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(long pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
