package com.group.genshinProg.model.DTO;

import java.io.Serializable;

public class AmountDTO implements Serializable {

    Integer amount;

    public AmountDTO(Integer amount) {
        this.amount = amount;
    }

    public void setAmount (Integer amount) {this.amount = amount;}

    public Integer getAmount () {return amount;}
}
