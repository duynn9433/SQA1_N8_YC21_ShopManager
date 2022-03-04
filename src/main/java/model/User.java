/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author duynn
 */
public class User implements Serializable{
    private int ID;
    private String username;
    private String password;
    private String name;
    private String position;
    private String phoneNumber;
    private boolean isActive;

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public User() {
        super();
    }

    public User(String username, String password, String name, String position,
                String birthday, String address, String mail, String phoneNumber) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + ID + ", username=" + username + ", password=" + password + ", name=" + name + ", position=" + position + ", phoneNumber=" + phoneNumber + ", isActive=" + isActive + '}';
    }
    
}
