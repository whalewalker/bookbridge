package com.bookbridge.services.contract;

import com.bookbridge.data.request.LoginRequest;
import com.bookbridge.data.request.RegisterRequest;
import com.bookbridge.data.response.LoginResponse;
import com.bookbridge.data.request.ResetPasswordRequest;
import com.bookbridge.data.response.Response;

public interface AuthService {
    Response<?> create(RegisterRequest request);

    Response<LoginResponse> login(LoginRequest request);

    Response<?> resetPassword(ResetPasswordRequest request);
}
