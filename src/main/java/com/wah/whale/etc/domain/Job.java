package com.wah.whale.etc.domain;

import com.wah.whale.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Table(name = "job")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long jobId;

    @Column(name = "job_name", nullable = false, unique = true)
    private String jobName;

    @ManyToMany(mappedBy = "jobs")
    private Set<User> users = new HashSet<>();
}
