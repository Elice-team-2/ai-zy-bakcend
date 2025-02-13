package com.elice.ai_zy.user.service;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.elice.ai_zy.user.entity.User;
import com.elice.ai_zy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ManagementAPI managementAPI;

    @Transactional
    public User getOrCreateUser(String auth0Id) {
        return userRepository.findByAuth0Id(auth0Id)
            .orElseGet(() -> createUserFromAuth0(auth0Id));
    }

    private User createUserFromAuth0(String auth0Id) {
        try {
            com.auth0.json.mgmt.users.User auth0User =
                managementAPI.users().get(auth0Id, null).execute().getBody();

            log.info("Auth0 user info - name: {}, email: {}",
                auth0User.getName(),
                auth0User.getEmail());

            User newUser = User.builder()
                .auth0Id(auth0Id)
                .email(auth0User.getEmail())
                .name(auth0User.getName())
                .build();

            return userRepository.save(newUser);

        } catch (Auth0Exception e) {
            log.error("Failed to fetch user info from Auth0", e);
            throw new RuntimeException("Failed to fetch user info from Auth0", e);
        }
    }

//    @Transactional
//    public User createUser(UserRegistrationDto dto) {
//        // 이미 존재하는 사용자인지 체크
//        if (userRepository.findByAuth0Id(dto.getAuth0Id()).isPresent()) {
//            throw new IllegalStateException("User already exists");
//        }
//
//        User user = User.builder()
//            .auth0Id(dto.getAuth0Id())
//            .email(dto.getEmail())
//            .name(dto.getName())
//            .build();
//
//        return userRepository.save(user);
//    }
}