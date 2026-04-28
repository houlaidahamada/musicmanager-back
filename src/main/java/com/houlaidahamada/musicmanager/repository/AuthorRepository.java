package com.houlaidahamada.musicmanager.repository;

import com.houlaidahamada.musicmanager.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByFullNameContainingIgnoreCase(String keyword);
}
