package com.houlaidahamada.musicmanager.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    private String biography;

    @ManyToMany(mappedBy = "authors")
    private Set<Song> songs = new HashSet<>();

    public Author() {}

    public Author(String fullName, String biography) {
        this.fullName = fullName;
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);
        song.getAuthors().add(this);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
        song.getAuthors().remove(this);
    }
}