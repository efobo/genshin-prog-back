package com.group.genshinProg.model.DTO;

import lombok.Data;

import java.io.Serializable;


public class PrayerDTO implements Serializable {
    String name;
    String rang;

    String userName;

    public PrayerDTO(String name, String rang, String userName) {
        this.name = name;
        this.rang = rang;
        this.userName = userName;
    }

    public void setName(String name) {this.name = name;}
    public void setRang(String rang) {this.rang = rang;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getName() {return name;}
    public String getRang() {return rang;}
    public String getUserName() {return userName;}
}
