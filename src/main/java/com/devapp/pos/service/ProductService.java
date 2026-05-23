package com.devapp.pos.service;

import com.devapp.pos.dto.request.ProductRequestDto;
import com.devapp.pos.dto.response.ProductResponseDto;
import com.devapp.pos.dto.response.paginate.PageResponseDto;
import java.util.UUID;

public interface ProductService {
    void createProduct(ProductRequestDto dto);
    void updateProduct(ProductRequestDto dto, UUID id);
    void deleteProduct(UUID id);
    ProductResponseDto findProductById(UUID id);
    PageResponseDto<ProductResponseDto> searchProducts(String searchText, int page, int size);
}

