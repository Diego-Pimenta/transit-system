package com.unifacs.transitsystem.service.impl;

import com.unifacs.transitsystem.exception.ResourceNotFoundException;
import com.unifacs.transitsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByCpf(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
