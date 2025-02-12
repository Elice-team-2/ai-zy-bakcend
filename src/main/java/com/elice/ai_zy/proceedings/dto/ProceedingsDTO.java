package com.elice.ai_zy.proceedings.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProceedingsDTO {

    private String title;

    private String contents;

    private User attendeeName;


}
