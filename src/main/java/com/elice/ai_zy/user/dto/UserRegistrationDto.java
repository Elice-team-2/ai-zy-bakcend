package com.elice.ai_zy.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String auth0Id;
    private String email;
    private String name;
}