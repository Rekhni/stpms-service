package com.rakhymzhan.stmps_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ФИО
    @Column(nullable = false, length = 255)
    private String fullName;

    // Номер телефона
    @Column(nullable = false, length = 50)
    private String phoneNumber;
}