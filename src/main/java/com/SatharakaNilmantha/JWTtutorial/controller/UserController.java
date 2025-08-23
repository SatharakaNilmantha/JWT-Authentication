package com.SatharakaNilmantha.JWTtutorial.controller;


import com.SatharakaNilmantha.JWTtutorial.dto.UserDto;
import com.SatharakaNilmantha.JWTtutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "auth")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("saveUser")
    public String saveUser (@RequestBody UserDto userDto)
    {
      String respond = userService.savedUser(userDto);
      return  respond ;
    }


    @GetMapping("/getAdmin")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userLists = userService.AllUsers();
        return ResponseEntity.ok(userLists);
    }

}
