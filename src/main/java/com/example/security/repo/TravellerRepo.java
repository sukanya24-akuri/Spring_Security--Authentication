package com.example.security.repo;

import com.example.security.entity.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TravellerRepo extends JpaRepository<Traveller, Integer>
{
    Optional<Traveller> findByEmail(String email);
}
