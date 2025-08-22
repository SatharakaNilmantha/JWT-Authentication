package com.SatharakaNilmantha.JWTtutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

// 👤 Custom UserDetailsService implement කරන class එක
// 💡 මේක Spring Security එකට user එකේ details (username, password, roles) provide කරනවා
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;    // 🔐 Password encode / hash කරන්න PasswordEncoder inject කරනවා


    // 🔎 User authenticate වෙන වෙලාවට Spring Security එක call කරන main method එක
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 👤 In-Memory user එකක් create කරනවා (DB එක use නොකර)
        UserDetails user = User.builder()
                .username("satharaka")                                         //  fixed username
                .password(passwordEncoder.encode("satharaka123"))  // password encode කරලා set කරනවා
                //.roles("USER")                                               // ඔබට අවශ්‍ය නම් roles / authorities දාන්න පුළුවන්
                .build();

        return user; // ✅ UserDetails object එක Spring Security එකට return කරනවා
    }
}
