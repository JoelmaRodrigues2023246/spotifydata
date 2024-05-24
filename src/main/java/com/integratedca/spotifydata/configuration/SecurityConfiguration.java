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

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()  // Allow access to login, register, and static resources
                    .requestMatchers("/user_account").authenticated()  // Only authenticated users can access the user account page
                    .anyRequest().authenticated()  // All other requests require authentication
            )
            .formLogin(formLogin -> 
                formLogin
                    .loginPage("/login").permitAll()  // Login page
                    .defaultSuccessUrl("/", true)  // Go to home page after successful login
                    .failureUrl("/login?error=true")  // Redirect to login page with error message if login fails
            )
            .logout(logout -> 
                logout
                    .logoutUrl("/logout")  // Custom logout URL
                    .logoutSuccessUrl("/login?logout=true")  // Go to login page after logout
                    .invalidateHttpSession(true)  // Invalidate the session
                    .clearAuthentication(true)  // Clear authentication information
                    .deleteCookies("JSESSIONID")  // Delete session cookies
                    .permitAll()  // Allow all users to access logout
            )
            .csrf(csrf -> 
                csrf.disable()  // Disable CSRF protection for simplicity
            )
            .sessionManagement(sessionManagement -> 
                sessionManagement
                    .maximumSessions(1)  // Limit the number of concurrent sessions per user
                    .maxSessionsPreventsLogin(true)  // Prevent new login if maximum sessions is reached
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // password encoding (BCryp)
    }
}