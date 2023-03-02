package com.koldex.horticola.api.oauth.repository;

import com.koldex.horticola.api.oauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //    Optional<User> findByEmailIgnoreCase(@NonNull String email);
    Optional<User> findByCpf(@NonNull String cpf);
}