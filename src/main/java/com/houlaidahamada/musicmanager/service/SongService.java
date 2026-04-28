package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.AuthorDTO;
import com.houlaidahamada.musicmanager.dto.SongDTO;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.repository.AuthorRepository;
import com.houlaidahamada.musicmanager.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AuthorRepository authorRepository;

    public SongService(SongRepository songRepository, AuthorRepository authorRepository) {
        this.songRepository = songRepository;
        this.authorRepository = authorRepository;
    }

    // -------------------------
    // GET ALL SONGS
    // -------------------------
    public List<SongDTO> getAllSongs() {
        return songRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // -------------------------
    // GET SONG BY ID
    // -------------------------
    public SongDTO getSongById(Long id) {
        Song song = songRepository.findById(id).orElseThrow();
        return toDTO(song);
    }

    // -------------------------
    // CREATE SONG
    // -------------------------
    public SongDTO createSong(SongDTO dto) {
        Song song = new Song();

        song.setTitle(dto.getTitle());
        song.setDescription(dto.getDescription());
        song.setCompleted(dto.isCompleted());

        Song saved = songRepository.save(song);
        return toDTO(saved);
    }

    // -------------------------
    // UPDATE SONG
    // -------------------------
    public SongDTO updateSong(Long id, SongDTO dto) {
        Song song = songRepository.findById(id).orElseThrow();

        song.setTitle(dto.getTitle());
        song.setDescription(dto.getDescription());
        song.setCompleted(dto.isCompleted());

        return toDTO(songRepository.save(song));
    }

    // -------------------------
    // DELETE SONG
    // -------------------------
    public void deleteSong(Long id) {
        Song song = songRepository.findById(id).orElseThrow();
        songRepository.delete(song);
    }

    // -------------------------
    // GET COMPLETED SONGS
    // -------------------------
    public List<SongDTO> getCompletedSongs() {
        return songRepository.findByCompleted(true)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // -------------------------
    // ADD AUTHOR TO SONG
    // -------------------------
    public SongDTO addAuthorToSong(Long songId, Long authorId) {
        Song song = songRepository.findById(songId).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();

        song.getAuthors().add(author);
        author.getSongs().add(song);

        return toDTO(songRepository.save(song));
    }

    // -------------------------
    // REMOVE AUTHOR FROM SONG
    // -------------------------
    public SongDTO removeAuthorFromSong(Long songId, Long authorId) {
        Song song = songRepository.findById(songId).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();

        song.getAuthors().remove(author);
        author.getSongs().remove(song);

        return toDTO(songRepository.save(song));
    }

    // -------------------------
    // SEARCH
    // -------------------------
    public List<SongDTO> searchSongs(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }

        return songRepository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // -------------------------
    // ENTITY → DTO
    // -------------------------
    private SongDTO toDTO(Song song) {
        SongDTO dto = new SongDTO();

        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setDescription(song.getDescription());
        dto.setCompleted(song.isCompleted());

        List<String> authors = song.getAuthors()
                .stream()
                .map(Author::getFullName)
                .toList();

        dto.setAuthors(authors);

        return dto;
    }
}