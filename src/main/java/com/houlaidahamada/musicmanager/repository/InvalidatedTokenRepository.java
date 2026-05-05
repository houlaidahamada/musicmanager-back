package com.houlaidahamada.musicmanager.repository;

import com.houlaidahamada.musicmanager.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, Long> {

    Optional<InvalidatedToken> findByToken(String token);
}