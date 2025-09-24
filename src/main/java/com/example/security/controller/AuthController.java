package com.example.security.controller;

import com.example.security.io.AuthRequest;
import com.example.security.io.AuthResponse;
import com.example.security.service.AppUserDetailsService;
import com.example.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AuthController
{
private  final AuthenticationManager authenticationManager;
private final AppUserDetailsService appUserDetailsService;
private final JwtUtil jwtUtil;
private final PasswordEncoder passwordEncoder;

@PostMapping("/login")
public AuthResponse login(@RequestBody  AuthRequest request)
{
authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
final UserDetails userDetails=appUserDetailsService.loadUserByUsername(request.getEmail());
final String token=jwtUtil.generateToken(userDetails);
return new AuthResponse(request.getEmail(),token);
}
}
