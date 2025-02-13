package com.elice.ai_zy.projects.service.impl;

import com.elice.ai_zy.projects.dto.ProjectPostDto;
import com.elice.ai_zy.projects.entity.Project;
import com.elice.ai_zy.projects.repository.ProjectRepository;
import com.elice.ai_zy.projects.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ResponseEntity<?> postProject(ProjectPostDto projectPostDto) {
        Project project = new Project();

        project.setId(UUID.randomUUID().toString());
        project.setTitle(projectPostDto.getTitle());
        project.setDescription(projectPostDto.getDescription());
        project.setTag(projectPostDto.getTag());
        projectRepository.save(project);
        //유저 권한테이블에 데이터생성 할 자리
        
        return ResponseEntity.ok(200);
    }
}
