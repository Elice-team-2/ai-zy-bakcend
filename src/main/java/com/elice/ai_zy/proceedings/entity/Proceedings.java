package com.elice.ai_zy.proceedings.entity;

import com.elice.ai_zy.proceedings.dto.ProceedingsTags;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Proceedings {
    @Id
    @GeneratedValue(generator = "proceedingsId")
    @GenericGenerator(name = "proceedingsId", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, name = "title"  , length = 255, unique = true)
    private String title;

    @Column(nullable = false, name = "contents", length = 255)
    private String contents;

    @Column(nullable = true, name = "tags", length = 255)
    private ProceedingsTags tags;

//    @Column(nullable = false, name = "attendeeName", length = 255)
//    private User attendeeName;
//    구현 필요

    @Column(nullable = false, name = "createdAt", length = 255)
    private LocalDateTime createdAt;

    @Column(nullable = true, name = "updatedAt", length = 255)
    private LocalDateTime updatedAt;

    @Column(nullable = true, name = "deletedAt", length = 255)
    private LocalDateTime deletedAt;
}
