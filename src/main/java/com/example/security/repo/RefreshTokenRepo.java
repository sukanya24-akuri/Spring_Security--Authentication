package com.example.security.repo;

import com.example.security.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Long>
{

    Optional<RefreshToken> findByToken(String token);
    void deleteByEmail(String email);
}
