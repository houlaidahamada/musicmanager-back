package com.houlaidahamada.musicmanager.dto.reference;

import com.houlaidahamada.musicmanager.entity.Reference;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReferenceCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String url;

    private Reference.ReferenceType type;

    private String description;
}