package com.elice.ai_zy.proceedings.entity;

import com.elice.ai_zy.proceedings.dto.ProceedingsTags;
import com.elice.ai_zy.projects.entity.Project;
import com.elice.ai_zy.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proceedings {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private String proceedingsId;

    @Column(nullable = false, length = 255, unique = true)
    private String title;

    @Column(nullable = false, length = 30000)
    private String contents;

    @Column(nullable = true)
    private ProceedingsTags tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", nullable = false)  // 명확한 컬럼명 사용
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @ElementCollection
    @Column(nullable = false)
    private List<String> attendeeNames;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @Column(nullable = true)
    private LocalDateTime deletedAt;
}
