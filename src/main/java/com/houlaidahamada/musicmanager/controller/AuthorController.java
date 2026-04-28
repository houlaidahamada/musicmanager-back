package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.AuthorDTO;
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
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO dto) {
        return authorService.createAuthor(dto);
    }

    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO dto) {
        return authorService.updateAuthor(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping("/search")
    public List<AuthorDTO> searchAuthors(@RequestParam String keyword) {
        return authorService.searchAuthors(keyword);
    }
}