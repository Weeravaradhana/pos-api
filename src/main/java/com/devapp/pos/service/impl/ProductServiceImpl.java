package com.devapp.pos.service.impl;

import com.devapp.pos.dto.request.ProductRequestDto;
import com.devapp.pos.dto.response.ProductResponseDto;
import com.devapp.pos.dto.response.paginate.PageResponseDto;
import com.devapp.pos.entity.Product;
import com.devapp.pos.exception.EntryNotfoundException;
import com.devapp.pos.repository.ProductRepo;
import com.devapp.pos.service.ProductService;
import com.devapp.pos.util.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Override
    public void createProduct(ProductRequestDto dto) {
        productRepo.save(productMapper.toProduct(dto));
    }

    @Override
    public void updateProduct(ProductRequestDto dto, UUID id) {
        Product selectedProduct = productRepo.findById(id)
                .orElseThrow(() -> new EntryNotfoundException("Product Entity Not Found"));

        selectedProduct.setDescription(dto.getDescription());
        selectedProduct.setQtyOnHand(dto.getQtyOnHand());
        selectedProduct.setUnitPrice(dto.getUnitPrice());
        productRepo.save(selectedProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
        Product selectedProduct = productRepo.findById(id)
                .orElseThrow(() -> new EntryNotfoundException("Product Entity Not Found"));
        productRepo.deleteById(selectedProduct.getId());
    }

    @Override
    public ProductResponseDto findProductById(UUID id) {
        Product selectedProduct = productRepo.findById(id)
                .orElseThrow(() -> new EntryNotfoundException("Product Entity Not Found"));
        return productMapper.toProductResponseDTO(selectedProduct);
    }

    @Override
    public PageResponseDto<ProductResponseDto> searchProducts(String searchText, int page, int size) {
       String text =  new StringBuilder().append("%").append(searchText).append("%").toString();
        return PageResponseDto.<ProductResponseDto>builder()
                .dataCount(productRepo.countAllProducts(text))
                .dataList(
                        productRepo.findAllProducts(text, PageRequest.of(page,size))
                                .stream()
                                .map(productMapper::toProductResponseDTO)
                                .toList()
                )
                .build();
    }
}
