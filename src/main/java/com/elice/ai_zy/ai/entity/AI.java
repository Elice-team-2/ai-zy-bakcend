package com.elice.ai_zy.ai.entity;


import com.elice.ai_zy.user.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

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

    @JoinColumn
    private String rawCommand;

    @JoinColumn
    private String response;

    @JoinColumn
    private String mode;

}
