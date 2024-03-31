package com.throchadev.projetovendedor.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
}
