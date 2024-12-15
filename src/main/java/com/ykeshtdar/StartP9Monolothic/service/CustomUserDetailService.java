package com.ykeshtdar.StartP9Monolothic.service;

import com.ykeshtdar.StartP9Monolothic.model.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;
import org.springframework.web.util.*;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Value("${service.url.patientAuthorizationBase}")
    private String findUsernameUrlBase;
    private final RestTemplate restTemplate;

    public CustomUserDetailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
//    private final LoginFormRepository loginFormRepository;

//    public CustomUserDetailService(LoginFormRepository loginFormRepository) {
//        this.loginFormRepository = loginFormRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("internalRequest","true");
        HttpEntity<String> entity =new HttpEntity<>(headers);
//        LoginForm loginForm = loginFormRepository.findByUsername(username)
        String baseUrl = "http://localhost:8082/login/findUsername";
        String loadUserByUsernameUrl = String.format("%s/findUsername",findUsernameUrlBase);
        String url = UriComponentsBuilder.fromHttpUrl(loadUserByUsernameUrl)
                .queryParam("username",username)
                .toUriString();

        ResponseEntity<LoginForm> response = restTemplate.exchange(url,
                HttpMethod.GET,
                entity,
                LoginForm.class);
//        LoginForm loginForm = restTemplate.getForObject(url,LoginForm.class);
//                .orElseThrow(()->new RuntimeException("username not founded"));


        UserDetails userDetails = User.builder()
                .username(response.getBody().getUsername())
                .password(response.getBody().getPassword())
                .build();
        return userDetails;
//        UserDetails userDetails = User.builder()
//                .username(loginForm.getUsername())
//                .password(loginForm.getPassword())
//                .build();
//        return userDetails;
    }
}

