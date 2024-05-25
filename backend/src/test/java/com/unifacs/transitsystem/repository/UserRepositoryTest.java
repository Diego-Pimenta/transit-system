package com.unifacs.transitsystem.repository;

import com.unifacs.transitsystem.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByCpf() {
        String cpf = "12345678902";
        User user = new User();
        user.setCpf(cpf);

        when(userRepository.findByCpf(cpf))
                .thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findByCpf(cpf);

        assertTrue(result.isPresent());
        assertEquals(cpf, result.get().getCpf());

        verify(userRepository, times(1))
                .findByCpf(cpf);
    }

    @Test
    public void testFindByCpf_userNotFound() {
        String cpf = "00000000000";

        when(userRepository.findByCpf(cpf))
                .thenReturn(Optional.empty());

        Optional<User> result = userRepository.findByCpf(cpf);

        assertTrue(result.isEmpty());

        verify(userRepository, times(1))
                .findByCpf(cpf);
    }
}