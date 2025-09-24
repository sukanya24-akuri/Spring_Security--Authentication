package com.example.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "travellers")
public class Traveller
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer travellerId;
    private String name;
    private String email;
    private String password;
}
