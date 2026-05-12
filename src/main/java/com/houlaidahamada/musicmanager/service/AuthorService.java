package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.author.AuthorCreateDto;
import com.houlaidahamada.musicmanager.dto.author.AuthorResponseDto;
import com.houlaidahamada.musicmanager.dto.author.AuthorUpdateDto;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.mapper.AuthorMapper;
import com.houlaidahamada.musicmanager.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    // -------------------------
    // GET ALL
    // -------------------------
    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDto)
                .toList();
    }

    // -------------------------
    // GET BY ID
    // -------------------------
    public AuthorResponseDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return authorMapper.toDto(author);
    }

    // -------------------------
    // CREATE
    // -------------------------
    public AuthorResponseDto createAuthor(AuthorCreateDto dto) {
        Author author = new Author();

        author.setFullName(dto.getFullName());
        author.setEmail(dto.getEmail());
        author.setRole(dto.getRole());
        
        return authorMapper.toDto(authorRepository.save(author));
    }

    // -------------------------
    // UPDATE
    // -------------------------
    public AuthorResponseDto updateAuthor(Long id, AuthorUpdateDto dto) {
        Author author = authorRepository.findById(id).orElseThrow();

        author.setFullName(dto.getFullName());
        //author.setBiography(dto.getBiography());

        return authorMapper.toDto(authorRepository.save(author));
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
//    public List<AuthorResponseDto> searchAuthors(String keyword) {
//        if (keyword == null || keyword.isBlank()) {
//            return List.of();
//        }
//
//        return authorRepository.findByFullNameContainingIgnoreCase(keyword)
//                .stream()
//                .map(authorMapper::toDto)
//                .toList();
//    }
}