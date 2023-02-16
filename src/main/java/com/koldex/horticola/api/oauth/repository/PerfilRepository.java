package com.koldex.horticola.api.oauth.repository;

import com.koldex.horticola.api.oauth.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}