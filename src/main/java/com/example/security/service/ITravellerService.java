package com.example.security.service;

import com.example.security.io.TravellerRequest;
import com.example.security.io.TravellerResponse;

public interface ITravellerService
{
    public TravellerResponse create(TravellerRequest request);
    public TravellerResponse getProfile();
}
