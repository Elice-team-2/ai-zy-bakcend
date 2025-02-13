package com.elice.ai_zy.proceedings.repository;

import com.elice.ai_zy.proceedings.entity.Proceedings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProceedingsRepository extends JpaRepository<Proceedings, UUID> { }