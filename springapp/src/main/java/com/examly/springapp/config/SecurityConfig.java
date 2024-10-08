package com.examly.springapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
   @Autowired
    private MyUserDetailsService myUserDetailsService;
 
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
   
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
 
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
 
    private final String[] PUBLIC_URL = {
        "/api/register",
        "/api/login"
    };
 
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       
    http.cors(Customizer.withDefaults()).addFilter(corsFilter())
    .csrf(csrf-> csrf.disable())
    .authorizeHttpRequests(auth-> auth.requestMatchers(PUBLIC_URL).permitAll()
    .anyRequest().authenticated())
    .exceptionHandling(e-> e.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
    .sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.authenticationProvider(authenticationProvider());
    http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
    return defaultSecurityFilterChain;
    }
 
// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     http
//         .cors(Customizer.withDefaults())
//         .addFilter(corsFilter())
//         .csrf(csrf -> csrf.disable())
//         .authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).permitAll().anyRequest().permitAll())
//         .exceptionHandling(e -> e.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
//         .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
   
//     http.authenticationProvider(authenticationProvider());
//     http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
   
//     DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
//     return defaultSecurityFilterChain;
// }
 
 
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception{
    return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(this.myUserDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
    return daoAuthenticationProvider;
    }
    @Bean
    public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOriginPattern("*");
    config.addAllowedHeader("Authorization");
    config.addAllowedHeader("Content-Type");
    config.addAllowedHeader("Accept");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("OPTIONS");
    config.setMaxAge(3600L);
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
    }
 
}
