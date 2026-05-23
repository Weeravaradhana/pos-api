package com.devapp.pos.dto.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponseDto {
    private UUID id;
    private String description;
    private Double unitPrice;
    private Integer qtyOnHand;
}