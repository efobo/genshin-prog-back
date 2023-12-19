package com.group.genshinProg.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @Size(min = 4, max = 20)
    @NotEmpty
    private String name;

    @Size(min = 3)
    @NotEmpty
    private String password;

    public UserDTO (String name, String password) {
        this.name = name;
        this.password = password;
    }
}
