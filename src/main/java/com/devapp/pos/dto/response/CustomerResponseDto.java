package com.devapp.pos.dto.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponseDto {
    private UUID id;
    private String name;
    private double salary;
    private String address;
}
