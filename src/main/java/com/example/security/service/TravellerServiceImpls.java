package com.example.security.service;

import com.example.security.entity.Traveller;
import com.example.security.io.TravellerRequest;
import com.example.security.io.TravellerResponse;
import com.example.security.repo.TravellerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TravellerServiceImpls implements ITravellerService {
    private final TravellerRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TravellerResponse create(TravellerRequest request) {
        Traveller traveller = convertToRequest(request);
        traveller = repo.save(traveller);
        return convertToResponse(traveller);
    }

    @Override
    public TravellerResponse getProfile() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        Traveller traveller = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("invalid email id"));
        return convertToResponse(traveller);
    }

    private Traveller convertToRequest(TravellerRequest request) {
        return Traveller.builder().name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }

    private TravellerResponse convertToResponse(Traveller traveller) {
        return TravellerResponse.builder()
                .name(traveller.getName())
                .email(traveller.getEmail())
                .password(passwordEncoder.encode(traveller.getPassword()))
                .build();
    }

}
