package com.elice.ai_zy.projects.service.impl;

import com.elice.ai_zy.global.eception.ProjectValidationException;
import com.elice.ai_zy.projects.dto.ProjectPostDto;
import com.elice.ai_zy.projects.entity.Project;
import com.elice.ai_zy.projects.repository.ProjectRepository;
import com.elice.ai_zy.projects.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectValidationException projectValidationException;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectValidationException projectValidationException) {
        this.projectRepository = projectRepository;
        this.projectValidationException = projectValidationException;
    }

    @Override
    public ResponseEntity<?> postProject(ProjectPostDto projectPostDto) {

        if (projectPostDto.getTitle() == null || projectPostDto.getTitle().trim().isEmpty()) {
            throw new ProjectValidationException("제목은 필수 입력값입니다");
        }
        if (projectPostDto.getDescription() == null || projectPostDto.getDescription().trim().isEmpty()) {
            throw new ProjectValidationException("설명은 필수 입력값입니다");
        }
        if (projectPostDto.getTag() == null) {
            throw new ProjectValidationException("태그는 필수 입력값입니다");
        }

        Project project = new Project();

        project.setId(UUID.randomUUID().toString());
        project.setTitle(projectPostDto.getTitle());
        project.setDescription(projectPostDto.getDescription());
        project.setTag(projectPostDto.getTag());
        projectRepository.save(project);
        //유저 권한테이블에 데이터생성 할 자리
        
        return ResponseEntity.ok(200);
    }

    @Override
    public ResponseEntity<?> updateProject(ProjectPostDto projectPostDto, String projectId) {

        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setTitle(projectPostDto.getTitle());
            project.setDescription(projectPostDto.getDescription());
            project.setTag(projectPostDto.getTag());

            projectRepository.save(project);

            return ResponseEntity.ok(200);
        } else {
            return ResponseEntity.badRequest().body("Project with ID " + projectId + " not found");
        }
    }

    @Override
    public Optional<Project> detailViewProject(String projectId) {
        return projectRepository.findById(projectId);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteProject(String projectId) {
        try{
        projectRepository.deleteById(projectId);
        return ResponseEntity.ok(200);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("프로젝트 삭제에 실패하였습니다.");
        }
    }
}
