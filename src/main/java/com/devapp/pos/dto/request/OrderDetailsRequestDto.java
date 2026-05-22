package com.devapp.pos.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailsRequestDto {
    @NotBlank(message = "Product id is Required")
    private UUID productId;
    @Min(value = 1, message = "Quantity must be at least 1 ")
    @Max(value = 10000, message = "Quantity must not exceed 10000")
    private int qty;
    @Positive(message = "Unit price must be a positive value")
    @DecimalMin(value = "0.01", message = "Unit price must be at least 0.01")
    @DecimalMax(value = "10000000", message = "Unit price must not exceed 10,000,000.00")
    private double unitPrice;
}
