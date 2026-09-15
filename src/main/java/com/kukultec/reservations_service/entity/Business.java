package com.kukultec.reservations_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "business")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 10)
    private String telephone;

    @Column(nullable = false)
    private Boolean active;

}
