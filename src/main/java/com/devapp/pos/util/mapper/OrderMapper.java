package com.devapp.pos.util.mapper;

import com.devapp.pos.dto.request.OrderDetailsRequestDto;
import com.devapp.pos.entity.Customer;
import com.devapp.pos.entity.CustomerOrder;
import com.devapp.pos.entity.OrderDetails;
import com.devapp.pos.entity.Product;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class OrderMapper {
    public CustomerOrder toCustomerOrder(
            Customer customer, List<OrderDetailsRequestDto> details, LocalDate date
    ) {
        return CustomerOrder.builder()
                .customer(customer)
                .totalCost(
                        calculate(details)
                ).date(date).build();
    }

    public double calculate(List<OrderDetailsRequestDto> dtos) {
        double total = 0;
        for (OrderDetailsRequestDto temp : dtos) {
            double unitPrice = temp.getUnitPrice();
            int qty = temp.getQty();
            total += qty * unitPrice;
        }
        return total;
    }

    public OrderDetails toOrderDetails(CustomerOrder order, Product product, double unitPrice, int qty) {
        return OrderDetails.builder().customerOrder(
                order
        ).product(
                product
        ).quantity(
                qty
        ).unitPrice(
                unitPrice
        ).build();
    }

}