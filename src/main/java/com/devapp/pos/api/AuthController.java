package com.devapp.pos.api;

import com.devapp.pos.dto.request.RegisterRequestDto;
import com.devapp.pos.service.AuthService;
import com.devapp.pos.util.StandardResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<StandardResponseDto> register
            (@Valid @RequestBody RegisterRequestDto dto){
        authService.register(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StandardResponseDto.builder()
                        .code(201)
                        .message("User created successfully")
                        .data(null)
                        .build());
    }

}
