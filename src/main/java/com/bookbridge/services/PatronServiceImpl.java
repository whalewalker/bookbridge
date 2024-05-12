package com.bookbridge.services;

import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.PatronRepo;
import com.bookbridge.data.request.LoginRequest;
import com.bookbridge.data.request.PatronRequest;
import com.bookbridge.data.response.LoginResponse;
import com.bookbridge.data.response.Response;
import com.bookbridge.security.JwtUtils;
import com.bookbridge.services.contract.PatronService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
public class PatronServiceImpl implements PatronService {
    private final PatronRepo patronRepo;
    private final ModelMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Cacheable(cacheNames = "patrons", key = "'all'")
    @Override
    public Response<Patron> getAll() {
        List<Patron> patrons = patronRepo.getAll();
        return successfulResponse(patrons);
    }

    @Cacheable(cacheNames = "patrons", key = "#id")
    @Override
    public Response<Patron> getById(Long id) {
        Patron patron = patronRepo.getById(id);
        return successfulResponse(List.of(patron));
    }

    @CachePut(cacheNames = "patrons", key = "#result.modelList.get(0).id")
    @Override
    public Response<Patron> create(PatronRequest request) {
        Patron patron = mapper.map(request, Patron.class);
        patron.setPassword(passwordEncoder.encode(request.getPassword()));
        return successfulResponse(List.of(patronRepo.create(patron)));
    }

    @Override
    public Response<LoginResponse> login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken((UserDetails) authentication.getDetails());

        return successfulResponse(List.of(new LoginResponse(token)));
    }

    @CachePut(cacheNames = "patrons", key = "#id")
    @Override
    public Response<Patron> update(Long id, PatronRequest request) {
        Patron patronToUpdate = patronRepo.getById(id);
        mapper.map(request, patronToUpdate);
        return successfulResponse(List.of(patronRepo.update(patronToUpdate)));
    }

    @CacheEvict(cacheNames = "patrons", allEntries = true)
    @Override
    public Response<?> delete(Long id) {
        patronRepo.delete(id);
        return successfulResponse(null);
    }

    @Override
    public Response<Patron> resetPassword(Long patronId, String password) {
        Patron patron = patronRepo.getById(patronId);
        patron.setPassword(passwordEncoder.encode(password));
        return successfulResponse(List.of(patronRepo.update(patron)));
    }
}
