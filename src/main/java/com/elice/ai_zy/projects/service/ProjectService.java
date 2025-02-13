package com.elice.ai_zy.projects.service;


import com.elice.ai_zy.projects.dto.ProjectPostDto;
import com.elice.ai_zy.projects.entity.Project;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ProjectService {
    ResponseEntity<?> postProject(ProjectPostDto projectPostDto);
    ResponseEntity<?> updateProject(ProjectPostDto projectPostDto, String projectId);
    //ResponseEntity<?> selectAllProject(ProjectPostDto projectPostDto);
    Optional<Project> detailViewProject(String projectId);
    ResponseEntity<?> deleteProject(String projectId);
}
