package com.example.security.service;

import com.example.security.entity.Traveller;
import com.example.security.repo.TravellerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final TravellerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
      Traveller traveller = repo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("invalid email address"));
        return new User(traveller.getEmail(),
                traveller.getPassword(),
                List.of(new SimpleGrantedAuthority(traveller.getRole())));
    }
}

