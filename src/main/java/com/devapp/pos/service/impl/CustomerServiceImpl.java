package com.devapp.pos.service.impl;

import com.devapp.pos.dto.request.CustomerRequestDto;
import com.devapp.pos.dto.response.CustomerResponseDto;
import com.devapp.pos.dto.response.paginate.PageResponseDto;
import com.devapp.pos.entity.Customer;
import com.devapp.pos.exception.EntryNotfoundException;
import com.devapp.pos.repository.CustomerRepo;
import com.devapp.pos.service.CustomerService;
import com.devapp.pos.util.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    @Override
    public void createCustomer(CustomerRequestDto dto) {
      customerRepo.save(customerMapper.toCustomer(dto));
    }

    @Override
    public void updateCustomer(CustomerRequestDto dto, UUID id) {
     Customer selectedCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new EntryNotfoundException("Customer not found for provided id"));

     selectedCustomer.setName(dto.getName());
     selectedCustomer.setAddress(dto.getAddress());
     selectedCustomer.setSalary(dto.getSalary());

     customerRepo.save(selectedCustomer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        Customer selectedCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new EntryNotfoundException("Customer not found for provided id"));

        customerRepo.deleteById(selectedCustomer.getId());
    }

    @Override
    public CustomerResponseDto findCustomerById(UUID id) {
        Customer selectedCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new EntryNotfoundException("Customer not found for provided id"));

        return customerMapper.toCustomerResponseDto(selectedCustomer);
    }

    @Override
    public List<CustomerResponseDto> findAll() {
        return customerRepo.findAll()
                .stream()
                .map(customerMapper::toCustomerResponseDto)
                .toList();
    }

    @Override
    public PageResponseDto<CustomerResponseDto> searchCustomers(String searchText, int page, int size) {
        searchText = "%" + searchText + "%";
        return PageResponseDto.<CustomerResponseDto>builder()
                .dataCount(customerRepo.countAllCustomers(searchText))
                .dataList(
                        customerRepo.findAllCustomers(searchText, PageRequest.of(page,size))
                                .stream()
                                .map(customerMapper::toCustomerResponseDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

}
