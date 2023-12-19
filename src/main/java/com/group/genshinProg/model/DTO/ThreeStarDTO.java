package com.group.genshinProg.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ThreeStarDTO implements Serializable {

    private int amount;
    private String userName;

    public ThreeStarDTO(int amount, String userName) {
        this.amount = amount;
        this.userName = userName;
    }

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

}
