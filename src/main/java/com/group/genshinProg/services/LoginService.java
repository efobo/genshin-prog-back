package com.group.genshinProg.services;

import com.group.genshinProg.exeption.UserAlreadyExistException;
import com.group.genshinProg.exeption.WrongCredentialsException;
import com.group.genshinProg.model.DTO.UserDTO;
import com.group.genshinProg.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;


    public LoginService(AccountService accountService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    public String login(UserDTO userDTOForLogin) {
        UserDTO userDTO = accountService.getAccountByName(userDTOForLogin.getName());
        if (userDTO == null || !passwordEncoder.matches(userDTOForLogin.getPassword(), userDTO.getPassword()))
            throw new WrongCredentialsException(userDTOForLogin);
        return jwtTokenUtil.generateAccessToken(userDTOForLogin.getName());
    }

    public String register(UserDTO userDTOForRegister) {
        UserDTO userDTO = accountService.getAccountByName(userDTOForRegister.getName());
        if (userDTO != null) {
            throw new UserAlreadyExistException();
        }

        userDTOForRegister.setPassword(passwordEncoder.encode(userDTOForRegister.getPassword()));
        accountService.saveUser(
                userDTOForRegister
        );
        return jwtTokenUtil.generateAccessToken(userDTOForRegister.getName());
    }

}
