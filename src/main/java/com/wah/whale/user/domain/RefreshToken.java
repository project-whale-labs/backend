package com.wah.whale.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 7)
public class RefreshToken implements Serializable {

    @Id
    private Long userId;

    private String refreshToken;

    public RefreshToken(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String refreshToken) {
        this.refreshToken = refreshToken;

        return this;
    }


}
