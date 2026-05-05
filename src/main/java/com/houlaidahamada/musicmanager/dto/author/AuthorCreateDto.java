package com.houlaidahamada.musicmanager.dto.author;

import com.houlaidahamada.musicmanager.entity.Author;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorCreateDto {

    @NotBlank
    private String fullName;

    private Author.AuthorRole role;

    private String email;
}
