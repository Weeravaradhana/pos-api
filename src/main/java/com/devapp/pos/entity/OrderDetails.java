package com.devapp.pos.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_details_id")
    private UUID orderDetailId;

    @Column(name = "unit_price")
    private double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quentity")
    private Integer quantity;
}
