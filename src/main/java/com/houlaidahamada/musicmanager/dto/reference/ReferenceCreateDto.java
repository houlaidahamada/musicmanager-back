package com.houlaidahamada.musicmanager.dto.reference;

import org.hibernate.validator.constraints.URL;

import com.houlaidahamada.musicmanager.entity.Reference;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReferenceCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    @URL(message = "Invalid URL format")
    private String url;

    @NotBlank
    private Reference.ReferenceType type;

    private String description;
}