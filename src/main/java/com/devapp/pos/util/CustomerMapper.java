package com.devapp.pos.util;

import com.devapp.pos.dto.request.CustomerRequestDto;
import com.devapp.pos.dto.response.CustomerResponseDto;
import com.devapp.pos.entity.Customer;
import com.devapp.pos.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequestDto dto) {
        if (dto == null) throw new ValidationException("CustomerRequestDto Not found");

        return Customer.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .salary(dto.getSalary())
                .build();
    }

    public CustomerResponseDto toCustomerResponseDto(Customer customer) {
        if (customer == null) throw new ValidationException("Customer Not found");

        return CustomerResponseDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .salary(customer.getSalary())
                .build();
    }
}
