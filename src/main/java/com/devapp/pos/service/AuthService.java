package com.devapp.pos.service;

import com.devapp.pos.dto.request.LoginRequestDto;
import com.devapp.pos.dto.request.RegisterRequestDto;
import com.devapp.pos.dto.response.AuthResponseDTO;

public interface AuthService {
    void register(RegisterRequestDto dto);
    AuthResponseDTO login(LoginRequestDto dto);
}