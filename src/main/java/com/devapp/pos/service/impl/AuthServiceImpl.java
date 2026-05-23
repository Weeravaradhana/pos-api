package com.devapp.pos.service.impl;

import com.devapp.pos.dto.request.LoginRequestDto;
import com.devapp.pos.dto.request.RegisterRequestDto;
import com.devapp.pos.dto.response.AuthResponseDTO;
import com.devapp.pos.entity.SystemUser;
import com.devapp.pos.enums.ROLE_TYPES;
import com.devapp.pos.exception.DuplicateEntryException;
import com.devapp.pos.repository.SystemUserRepo;
import com.devapp.pos.service.AuthService;
import com.devapp.pos.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SystemUserRepo systemUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

       SystemUser systemUser = systemUserRepo.findSystemUserByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found")
                        );

       String token = jwtUtil.generateAccessToken(systemUser);

       return AuthResponseDTO.builder()
               .token(token)
               .email(systemUser.getEmail())
               .fullName(systemUser.getFullName())
               .role(String.valueOf(systemUser.getRole()))
               .tokenType("Bearer")
               .build();
    }
}
