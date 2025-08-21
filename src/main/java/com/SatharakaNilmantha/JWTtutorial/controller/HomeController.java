package com.SatharakaNilmantha.JWTtutorial.controller;

import com.SatharakaNilmantha.JWTtutorial.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/home")
public class HomeController {


    private  final JWTService jwtService ;

    public HomeController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/GET")
    public  String GetString (){
        return "hi";
    }


    @PostMapping("/login")
    public  String postString (){

        String tokenRespond = jwtService.getJWTToken();
        return tokenRespond ;
    }

    @GetMapping("/username/{token}")
    public String getUserName (@PathVariable String token){
        String getRespond = jwtService.getUserName(token);
        return  getRespond ;
    }
}
