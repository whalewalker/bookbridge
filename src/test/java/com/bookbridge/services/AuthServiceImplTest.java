package com.bookbridge.services;

import com.bookbridge.data.model.User;
import com.bookbridge.data.repo.UserRepo;
import com.bookbridge.data.request.LoginRequest;
import com.bookbridge.data.request.RegisterRequest;
import com.bookbridge.data.request.ResetPasswordRequest;
import com.bookbridge.data.response.LoginResponse;
import com.bookbridge.data.response.Response;
import com.bookbridge.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(userRepo, authenticationManager, jwtUtils, passwordEncoder);
    }

    @Test
    void testCreate() {
        RegisterRequest request = new RegisterRequest("test@example.com", "password");
        when(passwordEncoder.encode(request.password())).thenReturn("encodedPassword");

        Response<?> response = authService.create(request);
        assertNotNull(response);

        verify(userRepo, times(1)).create(any(User.class));
    }

    @Test
    void testLogin() {
        LoginRequest request = new LoginRequest("test@example.com", "password");
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        String token = "jwtToken";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtils.generateToken(userDetails)).thenReturn(token);

        Response<LoginResponse> response = authService.login(request);
        assertNotNull(response);
        assertEquals(List.of(new LoginResponse(token)), response.getModelList());
    }

    @Test
    void testResetPassword() {
        ResetPasswordRequest request = new ResetPasswordRequest("user@email.com", "newPassword");
        User user = new User();
        when(userRepo.getById(1L)).thenReturn(user);
        when(passwordEncoder.encode(request.password())).thenReturn("encodedNewPassword");

        Response<?> response = authService.resetPassword(request);
        assertNotNull(response);

        verify(userRepo, times(1)).update(user);
    }

    @Test
    void testResetPasswordWhenUserNotFound() {
        when(userRepo.getById(1L)).thenReturn(null);
        ResetPasswordRequest request = new ResetPasswordRequest("user@email.com", "newPassword");
        Response<?> response = authService.resetPassword(request);
        assertNotNull(response);

        verify(userRepo, never()).update(any(User.class));
    }
}