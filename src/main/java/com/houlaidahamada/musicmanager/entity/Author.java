package com.houlaidahamada.musicmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    private AuthorRole role;

    private String email;

    @ManyToMany(mappedBy = "authors")
    private Set<Song> songs = new HashSet<>();

    public enum AuthorRole {
        COMPOSER, LYRICIST, ARRANGER, PRODUCER
    }
}