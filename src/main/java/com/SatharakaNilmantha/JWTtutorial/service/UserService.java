package com.SatharakaNilmantha.JWTtutorial.service;

import com.SatharakaNilmantha.JWTtutorial.dto.UserDto;
import com.SatharakaNilmantha.JWTtutorial.entity.UserEntity;
import com.SatharakaNilmantha.JWTtutorial.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String savedUser(UserDto userDto) {
        // Map DTO to Entity
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        // Encode the password before saving
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Save to DB
        userRepository.save(userEntity);

        return "Admin Details Saved Successfully";
    }


    public List<UserDto> AllUsers() {
        List userList = userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDto>>(){}.getType());
    }
}
