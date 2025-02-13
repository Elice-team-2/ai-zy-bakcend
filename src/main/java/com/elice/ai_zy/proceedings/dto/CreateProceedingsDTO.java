package com.elice.ai_zy.proceedings.dto;

import com.elice.ai_zy.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateProceedingsDTO {
    private String title;
    private String contents;
    private ProceedingsTags tags;
    private List<String> attendees;
    private User owner;
    private LocalDateTime createdAt;


    public List<String> getAttendees(String attendeeNames) {
        if (attendeeNames == null || attendeeNames.trim().isEmpty()) {
            return List.of();
        }

        String[] attendeeArray = attendeeNames.split(",");
        return Arrays.stream(attendeeArray)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
