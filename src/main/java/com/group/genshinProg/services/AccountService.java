package com.group.genshinProg.services;

import com.group.genshinProg.exeption.NotFoundEntityByIdException;
import com.group.genshinProg.model.ConverterDTO;
import com.group.genshinProg.model.DTO.UserDTO;
import com.group.genshinProg.model.entity.User;
import com.group.genshinProg.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;


    /*public UserDTO registration(UserDTO userDTO) {
        if (isExistAccountByNameAndPassword(userDTO.getName(), userDTO.getPassword())) {

        }
    }*/
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundEntityByIdException(User.class, id));
        return ConverterDTO.userToDTO(user);
    }


    public UserDTO saveUser(UserDTO userDTO) {
        User user = ConverterDTO.userDTOtoUser(userDTO);
        return ConverterDTO.userToDTO(userRepository.save(user));
    }

    public boolean isExistAccountByNameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password) != null;
    }


    public UserDTO getAccountByName(String name) {
        User user = userRepository.findByName(name);
        if (user == null) return null;
        return ConverterDTO.userToDTO(user);
    }



}
