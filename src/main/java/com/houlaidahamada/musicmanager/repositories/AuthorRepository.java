package com.houlaidahamada.musicmanager.repositories;

import com.houlaidahamada.musicmanager.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByFullNameContainingIgnoreCase(String keyword);
}
