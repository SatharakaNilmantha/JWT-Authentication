package com.SatharakaNilmantha.JWTtutorial.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {


//------------Step 01 → SecretKey generate කිරීම සහ එය final variable එකට assign කිරීම--------------------------------//
    private final SecretKey secretKey; // 🔑 JWT sign / verify කරන්න යොදා ගන්න secret key එක (final variable එකක්)

    // 🛠️ Constructor එක - Application run වෙද්දී SecretKey එකක් generate කරලා assign කරනවා
    public JWTService() {
        try {
            // 🎲 "HmacSHA256" algorithm එක use කරලා SecretKey generate කරනවා
            SecretKey k = KeyGenerator.getInstance("HmacSHA256").generateKey();

            // 🔒 Keys.hmacShaKeyFor() method එකෙන් byte[] එක convert කරලා real secretKey එකක් සකස් කරනවා
            secretKey = Keys.hmacShaKeyFor(k.getEncoded());
        } catch (Exception e) {
            // ⚠️ key generate වෙද්දී error ආවොත් RuntimeException එකක් throw කරනවා (aniwaryai)
            throw new RuntimeException(e);
        }
    }


//------------Step 02 → JWT Token generate කිරීම සහ එය return කිරීම --------------------------------//

    public String getJWTToken() {
        return Jwts.builder()
                .subject("satharaka")                                              // 👤 Token එකට subject (user name / user id) set කරනවා
                .issuedAt(new Date(System.currentTimeMillis()))                       // 🕒 Token issue කරන time එක
                .expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 15))     // ⏳ Expiration time (මේකේ 15 minutes)
                .signWith(secretKey)                                                  // 🔑 SecretKey එක use කරලා token එක sign කරනවා
                .compact();                                                           // 📦 Token එක final string එකක් return කරනවා
    }

//------------Step 03 → JWT Token එකෙන් username extract කිරීම --------------------------------//

    public String getUserName(String token) {
        try {
            return Jwts
                    .parser()                                           // 🛠️ Parser එකක් build කරනවා
                    .verifyWith(secretKey).build()                      // 🔑 Sign verify කරන්න SecretKey එක දෙනවා
                    .parseSignedClaims(token)                           // 📩 Signed token එක parse කරනවා
                    .getPayload()                                       // 📦 Token payload එක (claims)
                    .getSubject();                                      // 👤 Subject (username) extract කරනවා
        }catch (Exception e){
            return "token is not correct";
        }

    }

}
