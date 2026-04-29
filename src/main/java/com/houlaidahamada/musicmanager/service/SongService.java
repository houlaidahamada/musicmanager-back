package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.song.SongCreateDto;
import com.houlaidahamada.musicmanager.dto.song.SongResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongUpdateDto;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.mapper.SongMapper;
import com.houlaidahamada.musicmanager.repository.AuthorRepository;
import com.houlaidahamada.musicmanager.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AuthorRepository authorRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository, AuthorRepository authorRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.authorRepository = authorRepository;
        this.songMapper = songMapper;
    }

    // -------------------------
    // GET ALL SONGS
    // -------------------------
    public List<SongResponseDto> getAllSongs() {
        return songRepository.findAll()
                .stream()
                .map(songMapper::toDto)
                .toList();
    }

    // -------------------------
    // GET SONG BY ID
    // -------------------------
    public SongResponseDto getSongById(Long id) {
        Song song = songRepository.findById(id).orElseThrow();
        return songMapper.toDto(song);
    }

    // -------------------------
    // CREATE SONG
    // -------------------------
    public SongResponseDto createSong(SongCreateDto dto) {
        Song song = new Song();
        song.setTitle(dto.getTitle());
        song.setDescription(dto.getDescription());
        Song saved = songRepository.save(song);
        return songMapper.toDto(saved);
    }

    // -------------------------
    // UPDATE SONG
    // -------------------------
    public SongResponseDto updateSong(Long id, SongUpdateDto dto) {
        Song song = songRepository.findById(id).orElseThrow();

        song.setTitle(dto.getTitle());
        song.setDescription(dto.getDescription());
        song.setCompleted(dto.isCompleted());

        return songMapper.toDto(songRepository.save(song));
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
    public List<SongResponseDto> getCompletedSongs() {
        return songRepository.findByCompleted(true)
                .stream()
                .map(songMapper::toDto)
                .toList();
    }

    // -------------------------
    // ADD AUTHOR TO SONG
    // -------------------------
    public SongResponseDto addAuthorToSong(Long songId, Long authorId) {
        Song song = songRepository.findById(songId).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();

        song.getAuthors().add(author);
        author.getSongs().add(song);

        return songMapper.toDto(songRepository.save(song));
    }

    // -------------------------
    // REMOVE AUTHOR FROM SONG
    // -------------------------
    public SongResponseDto removeAuthorFromSong(Long songId, Long authorId) {
        Song song = songRepository.findById(songId).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();

        song.getAuthors().remove(author);
        author.getSongs().remove(song);

        return songMapper.toDto(songRepository.save(song));
    }

    // -------------------------
    // SEARCH
    // -------------------------
    public List<SongResponseDto> searchSongs(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }

        return songRepository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(songMapper::toDto)
                .toList();
    }
}