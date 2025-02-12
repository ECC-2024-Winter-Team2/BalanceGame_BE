package com.ecc.balancegame.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long userId;

    @Column(nullable = false, unique = true, length = 50)
    private String userName;
}