package com.devapp.pos.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequestDto {
    @NotBlank(message = "Description is Required")
    @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s,\\-]+$",message = "Description must contain only letters and spaces")
    private String description;
    @Positive(message = "Unit price must be a positive value")
    @DecimalMin(value = "0.01", message = "Unit price must be at least 0.01")
    @DecimalMax(value = "10000000", message = "Unit price must not exceed 10,000,000.00")
    private double unitPrice;
    @Min(value = 0, message = "QTY on hand cannot be negative")
    @Max(value = 100000, message = "QTY on hand must not exceed 100,000")
    private int qtyOnHand;
}
