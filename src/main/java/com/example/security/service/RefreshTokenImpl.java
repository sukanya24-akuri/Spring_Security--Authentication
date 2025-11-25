package com.example.security.service;

import com.example.security.entity.RefreshToken;
import com.example.security.repo.RefreshTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenImpl implements IRefreshToken
{
    private final  RefreshTokenRepo repo;
    @Override
    @Transactional
    public RefreshToken createRefreshToken(String email)
    {
        repo.deleteByEmail(email);
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setEmail(email);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpireTime(Instant.now().plusSeconds(7*24*60*60));
        return repo.save(refreshToken);
    }

    @Override
    @Transactional
    public RefreshToken checkExpiration(RefreshToken token)
    {
        if(token.getExpireTime().isBefore(Instant.now())){
         repo.delete(token);
            throw new RuntimeException("Refresh token expired");
        }
        return token;
    }

    @Override
    public Optional<RefreshToken> getToken(String token)
    {
        return repo.findByToken(token);
    }
}
