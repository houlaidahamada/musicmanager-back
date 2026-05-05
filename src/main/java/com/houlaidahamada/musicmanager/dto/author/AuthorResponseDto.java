package com.houlaidahamada.musicmanager.dto.author;

import com.houlaidahamada.musicmanager.entity.Author;
import lombok.Data;

@Data
public class AuthorResponseDto {

    private Long id;
    private String fullName;
    private Author.AuthorRole role;
    private String email;
}