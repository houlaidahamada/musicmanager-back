package com.houlaidahamada.musicmanager.dto.reference;

import com.houlaidahamada.musicmanager.entity.Reference;
import lombok.Data;

@Data
public class ReferenceResponseDto {

    private Long id;
    private String title;
    private String url;
    private Reference.ReferenceType type;
    private String description;
}