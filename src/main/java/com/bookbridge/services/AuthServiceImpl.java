package com.bookbridge.services;

import com.bookbridge.data.model.User;
import com.bookbridge.data.repo.UserRepo;
import com.bookbridge.data.request.LoginRequest;
import com.bookbridge.data.request.RegisterRequest;
import com.bookbridge.data.response.LoginResponse;
import com.bookbridge.data.request.ResetPasswordRequest;
import com.bookbridge.data.response.Response;
import com.bookbridge.exception.ResourceNotFoundException;
import com.bookbridge.security.JwtUtils;
import com.bookbridge.services.contract.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bookbridge.util.Util.successfulResponse;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Response<?> create(RegisterRequest request) {
        User user = User.builder().email(request.email()).build();
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepo.create(user);
        return successfulResponse(null);
    }

    @Override
    public Response<LoginResponse> login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

        return successfulResponse(List.of(new LoginResponse(token)));
    }

    @Override
    public Response<?> resetPassword(ResetPasswordRequest request) {
        User user = userRepo.getByEmail(request.email())
                .orElseThrow(()-> new ResourceNotFoundException("User not found with email: " + request.email()));
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepo.update(user);
        return successfulResponse(null);
    }
}
