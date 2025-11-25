package com.example.security.service;

import com.example.security.entity.RefreshToken;

import java.util.Optional;

public interface IRefreshToken
{
    RefreshToken createRefreshToken(String token);
    RefreshToken checkExpiration(RefreshToken expireTime);
    public Optional<RefreshToken> getToken(String token);
}
