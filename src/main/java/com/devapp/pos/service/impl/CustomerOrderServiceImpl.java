package com.devapp.pos.service.impl;

import com.devapp.pos.dto.request.CustomerOrderRequestDto;
import com.devapp.pos.entity.Customer;
import com.devapp.pos.entity.CustomerOrder;
import com.devapp.pos.entity.Product;
import com.devapp.pos.exception.EntryNotfoundException;
import com.devapp.pos.exception.ValidationException;
import com.devapp.pos.repository.CustomerRepo;
import com.devapp.pos.repository.OrderDetailsRepo;
import com.devapp.pos.repository.OrderRepo;
import com.devapp.pos.repository.ProductRepo;
import com.devapp.pos.service.CustomerOrderService;
import com.devapp.pos.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerRepo customerRepo;
    private final OrderRepo orderRepo;
    private final OrderDetailsRepo orderDetailsRepo;
    private final ProductRepo productRepo;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public void createOrder(CustomerOrderRequestDto dto) {
       Customer selectedCustomer =  customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new EntryNotfoundException("Customer not found for provided customerId"));

       CustomerOrder savedOrder = orderRepo.save(orderMapper.toCustomerOrder(
                selectedCustomer,dto.getOrderDetails(),dto.getDate()
        ));

        dto.getOrderDetails().forEach(e-> {
           Product selectedProduct =  productRepo.findById(e.getProductId())
                    .orElseThrow(()-> new EntryNotfoundException(String.format("Product not found %s", e.getProductId())));

           if(e.getQty()<=selectedProduct.getQtyOnHand()){
               orderDetailsRepo.save(orderMapper.toOrderDetails(
                       savedOrder,selectedProduct,e.getUnitPrice(),e.getQty()
               ));

               selectedProduct.setQtyOnHand(selectedProduct.getQtyOnHand()-e.getQty());
               productRepo.save(selectedProduct);
           }else {
               throw new ValidationException("Qty less than or equal to Qty on hand");
           }
        });

    }
}
