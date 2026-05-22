package com.devapp.pos.api;

import com.devapp.pos.dto.request.CustomerRequestDto;
import com.devapp.pos.service.CustomerService;
import com.devapp.pos.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<StandardResponseDto> createCustomer(@RequestBody CustomerRequestDto dto){
        customerService.createCustomer(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StandardResponseDto.builder()
                        .code(201)
                        .message("Customer created successfully")
                        .data(null)
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponseDto> updateCustomer(@RequestBody CustomerRequestDto dto,
                                                              @PathVariable UUID id){
        customerService.updateCustomer(dto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponseDto.builder()
                        .code(200)
                        .message("Customer updated successfully")
                        .data(null)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponseDto> findCustomerById(@PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponseDto.builder()
                        .code(200)
                        .message("Customer found successfully")
                        .data(customerService.findCustomerById(id))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponseDto> deleteCustomerById(@PathVariable UUID id){
        customerService.deleteCustomer(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(StandardResponseDto.builder()
                        .code(204)
                        .message("Customer deleted successfully")
                        .data(null)
                        .build());
    }

    @GetMapping("/list")
    public ResponseEntity<StandardResponseDto> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponseDto.builder()
                        .code(200)
                        .message("Customers found successfully")
                        .data(customerService.findAll())
                        .build());
    }

    @GetMapping
    public ResponseEntity<StandardResponseDto> searchCustomer(
            @RequestParam(defaultValue = "") String searchText,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponseDto.builder()
                        .code(200)
                        .message("Customers retrieved successfully")
                        .data(customerService.searchCustomers(searchText, page, size))
                        .build());

    }
}
