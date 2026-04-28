package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.AuthorDTO;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // -------------------------
    // GET ALL
    // -------------------------
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // -------------------------
    // GET BY ID
    // -------------------------
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return toDTO(author);
    }

    // -------------------------
    // CREATE
    // -------------------------
    public AuthorDTO createAuthor(AuthorDTO dto) {
        Author author = new Author();

        author.setFullName(dto.getFullName());
        author.setBiography(dto.getBiography());

        return toDTO(authorRepository.save(author));
    }

    // -------------------------
    // UPDATE
    // -------------------------
    public AuthorDTO updateAuthor(Long id, AuthorDTO dto) {
        Author author = authorRepository.findById(id).orElseThrow();

        author.setFullName(dto.getFullName());
        author.setBiography(dto.getBiography());

        return toDTO(authorRepository.save(author));
    }

    // -------------------------
    // DELETE
    // -------------------------
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        authorRepository.delete(author);
    }

    // -------------------------
    // SEARCH
    // -------------------------
    public List<AuthorDTO> searchAuthors(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }

        return authorRepository.findByFullNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // -------------------------
    // ENTITY → DTO
    // -------------------------
    private AuthorDTO toDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();

        dto.setId(author.getId());
        dto.setFullName(author.getFullName());
        dto.setBiography(author.getBiography());

        List<Long> songIds = author.getSongs()
                .stream()
                .map(Song::getId)
                .toList();

        dto.setSongIds(songIds);

        return dto;
    }
}