package com.devapp.pos.service.impl;

import com.devapp.pos.dto.request.LoginRequestDto;
import com.devapp.pos.dto.request.RegisterRequestDto;
import com.devapp.pos.dto.response.AuthResponseDTO;
import com.devapp.pos.entity.SystemUser;
import com.devapp.pos.enums.ROLE_TYPES;
import com.devapp.pos.exception.DuplicateEntryException;
import com.devapp.pos.repository.SystemUserRepo;
import com.devapp.pos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SystemUserRepo systemUserRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequestDto dto) {
      if (systemUserRepo.existsByEmail(dto.getEmail())) {
          throw new DuplicateEntryException("Email already exists");
      }

       SystemUser user = SystemUser.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFullName())
                .role(ROLE_TYPES.USER)
                .isActive(true)
                .build();
      systemUserRepo.save(user);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDto dto) {
        return null;
    }
}
