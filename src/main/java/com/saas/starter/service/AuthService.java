package com.saas.starter.service;

import com.saas.starter.dto.AuthResponse;
import com.saas.starter.dto.LoginRequest;
import com.saas.starter.dto.RegisterRequest;
import com.saas.starter.model.Role;
import com.saas.starter.model.Tenant;
import com.saas.starter.model.User;
import com.saas.starter.repository.TenantRepository;
import com.saas.starter.repository.UserRepository;
import com.saas.starter.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, TenantRepository tenantRepository,
                       PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already registered");
        }

        String tenantId = UUID.randomUUID().toString().substring(0, 8);
        String tenantName = request.tenantName() != null ? request.tenantName() : request.email().split("@")[0] + "-org";

        Tenant tenant = new Tenant(tenantId, tenantName);
        tenantRepository.save(tenant);

        User user = new User(request.firstName(), request.lastName(), request.email(),
                passwordEncoder.encode(request.password()), tenantId);
        user.setRole(Role.ADMIN);
        user = userRepository.save(user);

        String token = tokenProvider.generateToken(user.getId(), user.getEmail(),
                user.getRole().name(), user.getTenantId());

        return buildAuthResponse(token, user);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String token = tokenProvider.generateToken(user.getId(), user.getEmail(),
                user.getRole().name(), user.getTenantId());

        return buildAuthResponse(token, user);
    }

    private AuthResponse buildAuthResponse(String token, User user) {
        return new AuthResponse(token, "Bearer", 86400000L,
                new AuthResponse.UserInfo(user.getId(), user.getEmail(),
                        user.getFirstName(), user.getLastName(), user.getRole().name()));
    }
}
