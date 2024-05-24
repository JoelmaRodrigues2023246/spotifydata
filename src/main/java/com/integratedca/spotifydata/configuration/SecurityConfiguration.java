package com.integratedca.spotifydata.configuration;

import com.integratedca.spotifydata.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/login", "/register").permitAll()  // Allow access to login and register pages
                    .anyRequest().authenticated()  // All other requests require authentication
            )
            .formLogin(formLogin -> 
                formLogin
                    .loginPage("/login").permitAll()  // Login page
                    .defaultSuccessUrl("/", true)  // Go to home page after successful login
            )
            .logout(logout -> 
                logout
                    .logoutUrl("/perform_logout")  // Custom logout URL
                    .logoutSuccessUrl("/login?logout")  // Go to login page after logout
                    .invalidateHttpSession(true)  // Invalidate the session
                    .clearAuthentication(true)  // Clear authentication information
                    .deleteCookies("JSESSIONID")  // Delete session cookies
                    .permitAll()  // Allow all users to access logout
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}