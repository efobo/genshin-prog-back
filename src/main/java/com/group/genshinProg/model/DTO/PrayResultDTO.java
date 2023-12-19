package com.group.genshinProg.model.DTO;

import com.group.genshinProg.model.entity.PrayResult;
import com.group.genshinProg.model.enums.RangCode;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


public class PrayResultDTO implements Serializable {

    private Long id;
    private String name;

    private String rang;
    private String date;

    private String userName;

    public PrayResultDTO(Long id, String name, String rang, String date, String userName) {
        this.id = id;
        this.name = name;
        this.rang = rang;
        this.date = date;
        this.userName = userName;
    }

    public void setId (Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setRang(String rang) {this.rang = rang;}
    public void setDate (String date) {this.date = date;}
    public void setUserName(String userName) {this.userName = userName;}

    public Long getId() {return id;}
    public String getName() {
        return name;
    }

    public String getRang() {
        return rang;
    }

    public String getDate() {return date;}

    public String getUserName() {return userName;}
}
