package com.example.security.entity;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
}
