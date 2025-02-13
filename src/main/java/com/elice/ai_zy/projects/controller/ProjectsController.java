package com.elice.ai_zy.projects.controller;

import com.elice.ai_zy.projects.dto.ProjectPostDto;
import com.elice.ai_zy.projects.entity.Project;
import com.elice.ai_zy.projects.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PutMapping("/{projectId}/permissions")
    public ResponseEntity<?> updateProject(@RequestBody @Valid ProjectPostDto projectPostDto, @PathVariable String projectId){
        return projectService.updateProject(projectPostDto, projectId);
    }

//    @PostMapping
//    public ResponseEntity<?> selectAllProject(@RequestBody @Valid ProjectPostDto projectPostDto){
//        return projectService.postProject(projectPostDto);
//    }

    @GetMapping("/{projectId}")
    public Optional<Project> detailedViewProject(@PathVariable String projectId){
        return projectService.detailViewProject(projectId);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?>  deleteProject(@PathVariable String projectId){
        return projectService.deleteProject(projectId);
    }
}
