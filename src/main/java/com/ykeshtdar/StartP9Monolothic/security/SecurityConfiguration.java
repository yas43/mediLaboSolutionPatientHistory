package com.ykeshtdar.StartP9Monolothic.security;

import com.ykeshtdar.StartP9Monolothic.filter.*;
import com.ykeshtdar.StartP9Monolothic.service.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.web.filter.*;

import java.io.*;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfiguration {
    private final CustomUserDetailService customUserDetailService;
    private final JwtAuthFilter jwtAuthFilter;
//    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfiguration(CustomUserDetailService customUserDetailService, JwtAuthFilter jwtAuthFilter) {
        this.customUserDetailService = customUserDetailService;
//        this.jwtTokenFilter = jwtTokenFilter;
        this.jwtAuthFilter = jwtAuthFilter;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
//                    registry.requestMatchers(request ->
//                            "true".equals(request.getHeader("internalRequest"))).permitAll();
                    registry.anyRequest().authenticated();
                })
//                .addFilterBefore(new OncePerRequestFilter() {
//                    @Override
//                    protected void doFilterInternal(HttpServletRequest request,
//                                                    HttpServletResponse response,
//                                                    FilterChain filterChain) throws ServletException, IOException {
//                        // Bypass authentication by clearing the SecurityContext for internal requests
//                        if ("true".equals(request.getHeader("internalRequest"))) {
//                            SecurityContextHolder.clearContext();
//                        }
//                        filterChain.doFilter(request, response);
//                    }
//                }, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }











    //    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(registry -> {
////                    registry.requestMatchers("patient/signUp","patient/login","patient/home").permitAll();
////                    registry.anyRequest().permitAll();
//                    registry.requestMatchers(request -> "true".equals(request.getHeader("internalRequest"))).permitAll();
//                    registry.anyRequest().authenticated();
//                })
////                .formLogin(httpSecurityFormLoginConfigurer -> {
////                    httpSecurityFormLoginConfigurer
////                            .loginPage("/patient/login")
////                            .defaultSuccessUrl("/patient/login",true)
////                            .successHandler(new AuthenticationSuccessHandler());
////                })
////                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService (){
        return customUserDetailService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}


