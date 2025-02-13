package com.elice.ai_zy.projects.repository;

import com.elice.ai_zy.projects.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, byte[]> {
}
