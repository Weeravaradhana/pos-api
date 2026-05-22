package com.devapp.pos.service;

import com.devapp.pos.dto.request.CustomerRequestDto;
import com.devapp.pos.dto.response.CustomerResponseDto;
import com.devapp.pos.dto.response.paginate.PageResponseDto;
import java.util.List;
import java.util.UUID;

public interface CustomerService {
    void createCustomer(CustomerRequestDto dto);
    void updateCustomer(CustomerRequestDto dto, UUID id);
    void deleteCustomer(UUID id);
    CustomerResponseDto findCustomerById(UUID id);
    List<CustomerResponseDto> findAll();
    PageResponseDto<CustomerResponseDto> searchCustomers(String searchText, int page, int size);
}
