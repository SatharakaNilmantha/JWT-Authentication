package com.SatharakaNilmantha.JWTtutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {

        return httpSecurity

                // 🔒 CSRF ආරක්ෂාව අක්‍රීය කරනවා
                .csrf(c -> c.disable())

                // 📴 Session එකක් සෑදීම අක්‍රීය කරනවා
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // ✅ Authorization rules
                .authorizeHttpRequests(r -> r
                        .requestMatchers("/home/login").permitAll() // /login endpoint එකට අයෙකුටත් login වෙන්න පුළුවන් (permitAll)
                        .anyRequest().authenticated()// වෙනත් request එකක් ආවොත් -> Authentication verify වෙන්න ඕන
                )
                .httpBasic(Customizer.withDefaults())  // 🔑 Basic Authentication enable කරනවා (Default Login popup එක)
                .build();  // Filter chain එක build කරනවා
    }
}

