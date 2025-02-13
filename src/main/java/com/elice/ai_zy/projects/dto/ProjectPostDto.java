package com.elice.ai_zy.projects.dto;

import com.elice.ai_zy.projects.entity.ProjectTag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectPostDto {

    @NotBlank(message = "제목은 필수 입력값입니다")
    @Size(min = 1, max = 100, message = "제목은 1-100자 사이여야 합니다")
    private String title;
    @NotBlank(message = "설명은 필수 입력값입니다")
    @Size(max = 1000, message = "설명은 1000자를 넘을 수 없습니다")
    private String description;
    private ProjectTag tag;

    public ProjectPostDto() {
    }

    public ProjectPostDto(String title, String description, ProjectTag tag) {
        this.title = title;
        this.description = description;
        this.tag = tag;
    }
}
