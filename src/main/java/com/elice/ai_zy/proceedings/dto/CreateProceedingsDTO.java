package com.elice.ai_zy.proceedings.dto;

import com.elice.ai_zy.projects.entity.Project;
import com.elice.ai_zy.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class CreateProceedingsDTO {
    private String title;
    private String contents;
    private ProceedingsTags tags;
    private List<String> attendees;
    private User owner;
    private Project project;
    private LocalDateTime createdAt;

}
