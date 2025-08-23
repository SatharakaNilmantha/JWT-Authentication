package com.SatharakaNilmantha.JWTtutorial.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Document(collection = "users")
public class UserEntity {
    private String name;
    private String username;
    private String email;
    private String password;
}
