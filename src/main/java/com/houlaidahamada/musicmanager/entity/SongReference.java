package com.houlaidahamada.musicmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "song_references")
@Data
public class SongReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    private String title;

    private String url;

    @Enumerated(EnumType.STRING)
    private ReferenceType type;

    private String description;

    public enum ReferenceType {
        INSTRUMENTAL, INSPIRATION, SAMPLE, COVER
    }
}