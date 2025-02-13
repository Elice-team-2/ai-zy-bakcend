package com.elice.ai_zy.projects.controller;

import com.elice.ai_zy.projects.dto.ProjectPostDto;
import com.elice.ai_zy.projects.service.ProjectService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/project")
public class ProjectsController {

    private final ProjectService projectService;

    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<?> postProject(@RequestBody @Valid ProjectPostDto projectPostDto){
        return projectService.postProject(projectPostDto);
    }
}
