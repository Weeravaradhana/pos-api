package com.devapp.pos.service;

import com.devapp.pos.dto.request.CustomerOrderRequestDto;

public interface CustomerOrderService {
    void createOrder(CustomerOrderRequestDto dto);
}