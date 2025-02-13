package com.elice.ai_zy.projects.service;


import com.elice.ai_zy.projects.dto.ProjectPostDto;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<?> postProject(ProjectPostDto projectPostDto);
}
