package com.elice.ai_zy.proceedings.dto;


import com.elice.ai_zy.projects.entity.Project;
import com.elice.ai_zy.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class UpdateProceedingsDTO {
    private String title;
    private String contents;
    private ProceedingsTags tags;
    private User owner;
    private Project project;
    private List<String> attendees;
    private LocalDateTime updatedAt;


}
