package com.group.genshinProg.model.DTO;

import com.group.genshinProg.model.enums.Element;
import com.group.genshinProg.model.enums.RangCode;
import com.group.genshinProg.model.enums.WeaponType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class WeaponDTO implements Serializable {
    @NotEmpty
    private String name;

    private RangCode rang;

    private WeaponType weapon;


    public WeaponDTO(String name, RangCode rang, WeaponType weapon) {
        this.name = name;
        this.rang = rang;
        this.weapon = weapon;
    }

    public WeaponDTO() {}
}
