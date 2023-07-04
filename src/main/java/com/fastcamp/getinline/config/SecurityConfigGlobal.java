package com.fastcamp.getinline.config;

import com.fastcamp.getinline.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfigGlobal {

    @Autowired
    public void configureGlobal(
            AuthenticationManagerBuilder auth,
            PasswordEncoder passwordEncoder,
            AdminService adminService
    ) throws Exception {
        auth.userDetailsService(adminService).passwordEncoder(passwordEncoder);
    }

}
