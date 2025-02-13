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
public class UpdateProceedingsDTO {
    private String title;
    private String contents;
    private ProceedingsTags tags;
    private User owner;
    private List<String> attendees;
    private LocalDateTime updatedAt;

    public List<String> updateAttendees(String attendeeNames) {
        String[] attendeeArray = attendeeNames.split(",");
        return Arrays.stream(attendeeArray)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
