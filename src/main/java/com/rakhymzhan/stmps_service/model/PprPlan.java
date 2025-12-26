package com.rakhymzhan.stmps_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ppr_plans")
public class PprPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String type;

    @Column(nullable = false, length = 500)
    private String location;

    private LocalDate q1Date;
    private LocalDate q2Date;
    private LocalDate q3Date;
    private LocalDate q4Date;

}
