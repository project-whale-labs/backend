package com.wah.whale.user.domain;

import com.wah.whale.etc.domain.Job;
import com.wah.whale.etc.domain.TechStack;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "organization")
    private String organization;

    @Lob
    @Column(name = "introduction")
    private String introduction;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted = false;

    @Builder
    public User(String email,String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User update(String name) {
        this.name = name;

        return this;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @ManyToMany
    @JoinTable(name = "user_job"
            , joinColumns = @JoinColumn(name = "user_id")
            ,inverseJoinColumns = @JoinColumn(name = "job_id"))
    private Set<Job> jobs = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_techstack"
            ,joinColumns = @JoinColumn(name = "user_id")
            ,inverseJoinColumns = @JoinColumn(name = "tech_id"))
    private Set<TechStack> techStacks = new HashSet<>();

    @Override   // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override   // 사용자 패스워드 반환
    public String getPassword() {
        return password;
    }

    @Override   // 사용자 id 반환 (고유값)
    public String getUsername() {
        return email;
    }

    @Override   // 계정 만료 여부 반환
    public boolean isAccountNonExpired() {
        return true;    // true = 만료되지 x
    }

    @Override   // 계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        return true;    // true = 잠금되지 x
    }

    @Override   // 패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        return true;    // true = 만료되지 x
    }

    @Override   // 계정 사용 가능 여부 반환
    public boolean isEnabled() {
        return true;
    }
}
