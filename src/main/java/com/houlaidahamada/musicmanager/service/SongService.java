package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.song.SongCreateDto;
import com.houlaidahamada.musicmanager.dto.song.SongCreateResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongUpdateDto;
import com.houlaidahamada.musicmanager.entity.Author;
import com.houlaidahamada.musicmanager.entity.ArtistProfile;
import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.entity.User;
import com.houlaidahamada.musicmanager.mapper.SongMapper;
import com.houlaidahamada.musicmanager.repository.AuthorRepository;
import com.houlaidahamada.musicmanager.repository.ArtistProfileRepository;
import com.houlaidahamada.musicmanager.repository.SongRepository;
import com.houlaidahamada.musicmanager.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AuthorRepository authorRepository;
    private final ArtistProfileRepository artistProfileRepository;
    private final UserRepository userRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository, AuthorRepository authorRepository, ArtistProfileRepository artistProfileRepository, UserRepository userRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.authorRepository = authorRepository;
        this.artistProfileRepository = artistProfileRepository;
        this.userRepository = userRepository;
        this.songMapper = songMapper;
    }

    // -------------------------
    // GET ALL SONGS
    // -------------------------
    public Page<SongResponseDto> getSongs(Pageable pageable, Song.SongStatus status) {
        if (status != null) {
            return songRepository.findByStatus(status, pageable)
                    .map(songMapper::toDto);
        }
        return songRepository.findAll(pageable)
                .map(songMapper::toDto);
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
    public SongCreateResponseDto createSong(SongCreateDto dto) {
        // Get current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        // Get active artist profile (assuming first one for now)
        ArtistProfile artistProfile = artistProfileRepository.findByUserId(user.getId()).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No artist profile found for user"));

        Song song = new Song();
        song.setTitle(dto.getTitle());
        song.setDescription(dto.getDescription());
        song.setDuration(dto.getDuration());
        song.setStatus(Song.SongStatus.DRAFT);
        song.setArtistProfile(artistProfile);
        song.setCreatedAt(java.time.LocalDateTime.now());
        song.setUpdatedAt(java.time.LocalDateTime.now());

        Song saved = songRepository.save(song);
        return songMapper.toCreateResponseDto(saved);
//     // UPDATE SONG
//     // -------------------------
//     public SongResponseDto updateSong(Long id, SongUpdateDto dto) {
//         Song song = songRepository.findById(id).orElseThrow();

//         song.setTitle(dto.getTitle());
//         song.setDescription(dto.getDescription());
// //        song.setCompleted(dto.isCompleted());

//         return songMapper.toDto(songRepository.save(song));
//     }

    // -------------------------
    // DELETE SONG
    // // -------------------------
    // public void deleteSong(Long id) {
    //     Song song = songRepository.findById(id).orElseThrow();
    //     songRepository.delete(song);
    // }

    // -------------------------
    // GET COMPLETED SONGS
    // -------------------------
//    public List<SongResponseDto> getCompletedSongs() {
//        return songRepository.findByCompleted(true)
//                .stream()
//                .map(songMapper::toDto)
//                .toList();
//    }

    // -------------------------
    // ADD AUTHOR TO SONG
    // -------------------------
    // public SongResponseDto addAuthorToSong(Long songId, Long authorId) {
    //     Song song = songRepository.findById(songId).orElseThrow();
    //     Author author = authorRepository.findById(authorId).orElseThrow();

    //     song.getAuthors().add(author);
    //     author.getSongs().add(song);

    //     return songMapper.toDto(songRepository.save(song));
    // }

    // -------------------------
    // REMOVE AUTHOR FROM SONG
    // -------------------------
    // public SongResponseDto removeAuthorFromSong(Long songId, Long authorId) {
    //     Song song = songRepository.findById(songId).orElseThrow();
    //     Author author = authorRepository.findById(authorId).orElseThrow();

    //     song.getAuthors().remove(author);
    //     author.getSongs().remove(song);

    //     return songMapper.toDto(songRepository.save(song));
    // }
}}