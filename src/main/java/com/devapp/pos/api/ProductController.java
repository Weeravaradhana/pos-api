package com.devapp.pos.api;

import com.devapp.pos.dto.request.ProductRequestDto;
import com.devapp.pos.dto.response.ProductResponseDto;
import com.devapp.pos.dto.response.paginate.PageResponseDto;
import com.devapp.pos.service.ProductService;
import com.devapp.pos.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<StandardResponseDto> createProduct(@RequestBody ProductRequestDto Dto) {
        productService.createProduct(Dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StandardResponseDto.builder()
                        .code(201)
                        .message("Product created successfully")
                        .data(null)
                        .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<StandardResponseDto> updateProduct(
            @RequestBody ProductRequestDto Dto,
            @PathVariable UUID id) {
        productService.updateProduct(Dto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponseDto.builder()
                        .code(200)
                        .message("Product updated successfully")
                        .data(null)
                        .build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponseDto> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(StandardResponseDto.builder()
                        .code(204)
                        .message("Product deleted successfully")
                        .data(null)
                        .build());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<StandardResponseDto> findProductById(@PathVariable UUID id) {
        ProductResponseDto Product = productService.findProductById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponseDto.builder()
                        .code(200)
                        .message("Product retrieved successfully")
                        .data(Product)
                        .build());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<StandardResponseDto> searchProducts(
            @RequestParam(defaultValue = "") String searchText,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResponseDto<ProductResponseDto> result = productService.searchProducts(searchText, page, size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponseDto.builder()
                        .code(200)
                        .message("Products retrieved successfully")
                        .data(result)
                        .build());
    }
}