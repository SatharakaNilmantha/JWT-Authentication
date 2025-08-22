package com.SatharakaNilmantha.JWTtutorial.config;

import com.SatharakaNilmantha.JWTtutorial.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                .authenticationProvider(daoAuthenticationProvider())
                .build();  // Filter chain එක build කරනවා
    }


    // 🔑 DaoAuthenticationProvider එක configure කරනවා
    // (UserDetailsService + PasswordEncoder එක use කරන authentication provider එකක්)
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider (){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    // 🔐 Password encode / hash කරන්න use කරන PasswordEncoder එක define කරනවා
    // 💡 User එක DB එකේ save වෙලා තියෙන password එක compare කරන වෙලාවට මේ encoder එක භාවිතා වෙනවා
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);   // (BCrypt algorithm එක use කරනවා – strength = 12)
    }

    // 👤 User details load කරන්න custom UserDetailsService එකක් register කරනවා
    // 💡 Login වෙන්න යන user එකගේ username & password load කරලා validate කරනවා
    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }



}

