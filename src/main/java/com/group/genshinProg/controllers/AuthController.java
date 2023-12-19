package com.group.genshinProg.controllers;

import com.group.genshinProg.exeption.UserAlreadyExistException;
import com.group.genshinProg.exeption.WrongCredentialsException;
import com.group.genshinProg.model.DTO.AuthResponseDTO;
import com.group.genshinProg.model.DTO.UserDTO;
import com.group.genshinProg.services.LoginService;
import com.group.genshinProg.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    //mvn spring-boot:run
    @Autowired
    private final LoginService loginService;



    @CrossOrigin
    @PostMapping(path="/register/", consumes = "application/json")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {
        try {
            String token = loginService.register(userDTO);
            System.out.println(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (UserAlreadyExistException ex) {
            System.out.println("User already exist");
            return new ResponseEntity<>("User already exist", HttpStatus.FORBIDDEN);
        }

    }

    @CrossOrigin
    @PostMapping(path="/login/", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody @Valid UserDTO userDTO) {
        try {
            String token = loginService.login(userDTO);
            System.out.println(token);
            return ResponseEntity.ok(token);
        }catch (WrongCredentialsException ex) {
            System.out.println("Wrong credentials");
            return new ResponseEntity<>("Wrong credentials", HttpStatus.FORBIDDEN);
        }
    }
}
