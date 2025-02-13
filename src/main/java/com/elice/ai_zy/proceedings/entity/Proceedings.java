package com.elice.ai_zy.proceedings.entity;

import com.elice.ai_zy.proceedings.dto.ProceedingsTags;
import com.elice.ai_zy.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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


    @Column(nullable = false, name = "title"  , length = 255, unique = true)
    private String title;

    @Column(nullable = false, name = "contents", length = 30000)
    private String contents;

    @Column(nullable = true, name = "tags", length = 255)
    private ProceedingsTags tags;

//    @JoinColumn(nullable = true)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User owner;

    @ElementCollection
    @Column(nullable = false, name = "attendeeName", length = 255)
    private List<String> attendeeNames;

    @Column(nullable = false, name = "createdAt", length = 255)
    private LocalDateTime createdAt;

    @Column(nullable = true, name = "updatedAt", length = 255)
    private LocalDateTime updatedAt;

    @Column(nullable = true, name = "deletedAt", length = 255)
    private LocalDateTime deletedAt;
}
