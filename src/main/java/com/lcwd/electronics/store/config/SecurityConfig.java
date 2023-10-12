package com.lcwd.electronics.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.beans.Encoder;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        //user create

        UserDetails normal = User.builder()
                .username("vinay")
                .password(passwordEncoder().encode("vinay"))
                .roles("normal")
                .build();

        UserDetails admin = User.builder()
                .username("ram")
                .password(passwordEncoder().encode("ram"))
                .roles("admin")
                .build();


        //InMemoryUserDetailsManager-

        return new InMemoryUserDetailsManager(normal, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
