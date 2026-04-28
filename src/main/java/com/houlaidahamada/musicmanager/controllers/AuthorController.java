package com.houlaidahamada.musicmanager.controllers;

import com.houlaidahamada.musicmanager.entities.Author;
import com.houlaidahamada.musicmanager.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Long id){
        return authorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author){
        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails){
        Author author = authorRepository.findById(id).orElse(null);
        if(author != null) {
            author.setFullName(authorDetails.getFullName());
            author.setBiography(authorDetails.getBiography());
            return authorRepository.save(author);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        authorRepository.deleteById(id);
    }

    @GetMapping("/authors/search")
    public List<Author> searchAuthors(@RequestParam String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }
        return authorRepository.findByFullNameContainingIgnoreCase(keyword);
    }
}
