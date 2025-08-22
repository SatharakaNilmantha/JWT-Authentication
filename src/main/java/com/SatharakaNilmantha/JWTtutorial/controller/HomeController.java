package com.SatharakaNilmantha.JWTtutorial.controller;

import com.SatharakaNilmantha.JWTtutorial.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

//--------------------------------------------------------------------
    private  final JWTService jwtService ;

    public HomeController(JWTService jwtService) {
        this.jwtService = jwtService;
    }


   /*
    @Autowired
    private JWTService jwtService;  // directly injected here
   */

//------------------------------------------------------------------------


    @GetMapping("/GET")
    public  String GetString (){
        return "hi";
    }


    @PostMapping("/login")
    public  String postString (){

        String tokenRespond = jwtService.getJWTToken();
        return tokenRespond ;
    }

    @GetMapping("/username")
    public String getUserName (@RequestParam String token){
        String getRespond = jwtService.getUserName(token);
        return  getRespond ;
    }
}
