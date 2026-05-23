package com.devapp.pos.util.mapper;

import com.devapp.pos.dto.request.ProductRequestDto;
import com.devapp.pos.dto.response.ProductResponseDto;
import com.devapp.pos.entity.Product;
import com.devapp.pos.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequestDto dto) {
        if (dto == null) throw new ValidationException("DTO Not Found");
        return Product.builder().description(dto.getDescription())
                .qtyOnHand(dto.getQtyOnHand()).unitPrice(dto.getUnitPrice()).build();
    }

    public ProductResponseDto toProductResponseDTO(Product product) {
        if (product == null) throw new ValidationException("Product Entity Not Found");
        return ProductResponseDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .qtyOnHand(product.getQtyOnHand())
                .unitPrice(product.getUnitPrice())
                .build();
    }
}