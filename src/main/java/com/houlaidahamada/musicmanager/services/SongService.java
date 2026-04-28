package com.houlaidahamada.musicmanager.services;

import com.houlaidahamada.musicmanager.entities.Author;
import com.houlaidahamada.musicmanager.entities.Song;
import com.houlaidahamada.musicmanager.repositories.AuthorRepository;
import com.houlaidahamada.musicmanager.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Song addAuthorToSong(Long songId, Long authorId) {
        Song song = songRepository.findById(songId).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();

        song.getAuthors().add(author);

        return songRepository.save(song);
    }

    public Song removeAuthorFromSong(Long songId, Long authorId) {
        Song song = songRepository.findById(songId).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();

        song.getAuthors().remove(author);

        return songRepository.save(song);
    }
}