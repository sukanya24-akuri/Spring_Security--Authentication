package com.example.security.controller;

import com.example.security.io.TravellerRequest;
import com.example.security.io.TravellerResponse;
import com.example.security.service.TravellerServiceImpls;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class TravellerControllerAdmin {
    private final TravellerServiceImpls travellerServiceImpls;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public TravellerResponse createProfile(@RequestBody TravellerRequest request)
    {
        request.setRole("ROLE_ADMIN");
        TravellerResponse response = travellerServiceImpls.create(request);

        return response;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TravellerResponse getProfile() {
        return travellerServiceImpls.getProfile();
    }
}
