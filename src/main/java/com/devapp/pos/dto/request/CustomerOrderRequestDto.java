package com.devapp.pos.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerOrderRequestDto {
    @NotNull(message = "Order date is Required")
    @PastOrPresent(message = "Order date cannot be a future date")
    private LocalDate date;
    @NotBlank(message = "Customer id is Required")
    private UUID customerId;
    @NotNull(message = "Order details cannot be  null")
    @NotEmpty(message = "Order must contain at least one product")
    @Size(max = 100, message = "Order  cannot contain more than 100 product")
    @Valid
    private List<OrderDetailsRequestDto> orderDetails;
}
