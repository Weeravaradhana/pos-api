package com.devapp.pos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID id;

    private String description;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "qty_on_hand")
    private int qtyOnHand;
}
