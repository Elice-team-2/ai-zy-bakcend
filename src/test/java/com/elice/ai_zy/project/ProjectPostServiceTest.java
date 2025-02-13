package com.elice.ai_zy.project;

import com.elice.ai_zy.projects.dto.ProjectPostDto;
import com.elice.ai_zy.projects.entity.Project;
import com.elice.ai_zy.projects.repository.ProjectRepository;
import com.elice.ai_zy.projects.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.elice.ai_zy.projects.entity.ProjectTag;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProjectPostServiceTest {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private ProjectPostDto projectPostDto;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the ProjectPostDto with valid data
        projectPostDto = new ProjectPostDto("Project Title", "Project Description", ProjectTag.개발);
    }

    @Test
    public void testPostProject() {
        // Arrange
        Project project = new Project();
        project.setId(UUID.randomUUID().toString());
        project.setTitle(projectPostDto.getTitle());
        project.setDescription(projectPostDto.getDescription());
        project.setTag(projectPostDto.getTag());

        // Mock the behavior of the projectRepository
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // Act
        ResponseEntity<?> response = projectService.postProject(projectPostDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(projectRepository, times(1)).save(any(Project.class)); // Verify save method is called
    }
}
