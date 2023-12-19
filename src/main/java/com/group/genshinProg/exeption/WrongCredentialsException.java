package com.group.genshinProg.exeption;

import com.group.genshinProg.model.DTO.UserDTO;

public class WrongCredentialsException extends RuntimeException {
    private final UserDTO userDTO;
    public WrongCredentialsException(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public String getDescription() {
        return "There is no account with a login " + userDTO.getName() + " and password " + userDTO.getPassword();
    }

}
