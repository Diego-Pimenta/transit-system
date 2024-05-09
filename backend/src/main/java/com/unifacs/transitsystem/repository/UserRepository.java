package com.unifacs.transitsystem.repository;

import com.unifacs.transitsystem.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByCpf(String cpf);
}
