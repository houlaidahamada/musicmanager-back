package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.author.AuthorCreateDto;
import com.houlaidahamada.musicmanager.dto.author.AuthorResponseDto;
import com.houlaidahamada.musicmanager.dto.author.AuthorUpdateDto;
import com.houlaidahamada.musicmanager.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorResponseDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorResponseDto getAuthor(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public AuthorResponseDto createAuthor(@RequestBody AuthorCreateDto dto) {
        return authorService.createAuthor(dto);
    }

    @PutMapping("/{id}")
    public AuthorResponseDto updateAuthor(@PathVariable Long id, @RequestBody AuthorUpdateDto dto) {
        return authorService.updateAuthor(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping("/search")
    public List<AuthorResponseDto> searchAuthors(@RequestParam String keyword) {
        return authorService.searchAuthors(keyword);
    }
}