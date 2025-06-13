package com.wah.whale.etc.domain;

import com.wah.whale.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Table(name = "techstack")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id", updatable = false)
    private Long techId;

    @Column(name = "tech_name",nullable = false, unique = true)
    private String techName;

    @ManyToMany(mappedBy = "techStacks")
    private Set<User> users = new HashSet<>();
}
