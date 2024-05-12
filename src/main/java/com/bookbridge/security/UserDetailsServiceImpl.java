
package com.bookbridge.security;

import com.bookbridge.data.model.Patron;
import com.bookbridge.data.repo.PatronRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PatronRepo patronRepo;

    public UserDetailsServiceImpl(PatronRepo patronRepo) {
        this.patronRepo = patronRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patron patron = patronRepo.getByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new User(
                patron.getEmail(),
                patron.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))        );
    }
}