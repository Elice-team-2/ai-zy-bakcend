package com.elice.ai_zy.user.service;

import com.elice.ai_zy.user.entity.User;
import com.elice.ai_zy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User getOrCreateUser(Jwt jwt) {
        log.info("유저 저장");
        String auth0Id = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");

        // 기존 유저 조회
        User existingUser = userRepository.findByAuth0Id(auth0Id).orElse(null);
        if (existingUser != null) {
            return existingUser; // 기존 유저 반환
        }
        // 새로운 유저 생성 및 저장
        User newUser = User.builder()
            .auth0Id(auth0Id)
            .email(email)
            .name(name)
            .build();

        return userRepository.save(newUser);
    }
}