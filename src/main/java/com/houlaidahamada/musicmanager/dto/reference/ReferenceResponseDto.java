package com.houlaidahamada.musicmanager.dto.reference;

import com.houlaidahamada.musicmanager.entity.SongReference;
import lombok.Data;

@Data
public class ReferenceResponseDto {

    private Long id;
    private String title;
    private String url;
    private SongReference.ReferenceType type;
    private String description;
}