package com.elice.ai_zy.user.controller;

import com.elice.ai_zy.user.dto.UserRegistrationDto;
import com.elice.ai_zy.user.entity.User;
import com.elice.ai_zy.user.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@AuthenticationPrincipal Jwt jwt) {
        // JWT에서 Auth0 user ID 추출
        String auth0Id = jwt.getSubject();
        User user = userService.getOrCreateUser(auth0Id);
        return ResponseEntity.ok(user);
    }

//    @PostMapping("/api/public/register")
//    public ResponseEntity<?> register(@RequestBody UserRegistrationDto registrationDto) {
//        User newUser = userService.createUser(registrationDto);
//        return ResponseEntity.ok(newUser);
//    }
}
