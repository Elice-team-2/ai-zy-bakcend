package com.elice.ai_zy.ai.entity;


import com.elice.ai_zy.user.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Table(name = "ai")
@Entity
public class AI {

    @Column(name = "ai_id", columnDefinition = "CHAR(36) UNIQUE NOT NULL")
    @GeneratedValue
    @UuidGenerator
    @Id
    private String aiId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String rawCommand;

    @Column
    private String response;

    @Column
    private String mode;

    @Column
    private LocalDateTime createAt;

}
