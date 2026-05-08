package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.lyrics.LyricsCreateDto;
import com.houlaidahamada.musicmanager.dto.lyrics.LyricsResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongCreateDto;
import com.houlaidahamada.musicmanager.dto.song.SongCreateResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongStatusUpdateDto;
import com.houlaidahamada.musicmanager.dto.song.SongUpdateDto;
import com.houlaidahamada.musicmanager.entity.ArtistProfile;
import com.houlaidahamada.musicmanager.entity.Lyrics;
import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.entity.User;
import com.houlaidahamada.musicmanager.mapper.LyricsMapper;
import com.houlaidahamada.musicmanager.mapper.SongMapper;
import com.houlaidahamada.musicmanager.repository.ArtistProfileRepository;
import com.houlaidahamada.musicmanager.repository.SongRepository;
import com.houlaidahamada.musicmanager.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SongService {

    private final SongRepository songRepository;
    private final ArtistProfileRepository artistProfileRepository;
    private final UserRepository userRepository;
    private final SongMapper songMapper;
    private final LyricsMapper lyricsMapper;

    public SongService(SongRepository songRepository, ArtistProfileRepository artistProfileRepository, UserRepository userRepository, SongMapper songMapper, LyricsMapper lyricsMapper) {
        this.songRepository = songRepository;
        this.artistProfileRepository = artistProfileRepository;
        this.userRepository = userRepository;
        this.songMapper = songMapper;
        this.lyricsMapper = lyricsMapper;
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
        // Force initialization of lazy-loaded collections to avoid LazyInitializationException
        song.getAuthors().size();
        song.getThemes().size();
        song.getReferences().size();
        if (song.getLyrics() != null) {
            song.getLyrics().getId();
        }
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
    }

    // -------------------------
// UPDATE SONG
// -------------------------
    public SongResponseDto updateSong(Long id, SongUpdateDto dto) {
        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("Song not found"));

        if (dto.getTitle() != null) {
            if (dto.getTitle().trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be blank");
            }
            song.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            song.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null) {
            song.setStatus(dto.getStatus());
        }
        if (dto.getDuration() != null) {
            if (dto.getDuration() <= 0) {
                throw new IllegalArgumentException("Duration must be positive");
            }
            song.setDuration(dto.getDuration());
        }

        song.setUpdatedAt(java.time.LocalDateTime.now());

        Song updatedSong = songRepository.save(song);
        return songMapper.toDto(updatedSong);
    }

    // -------------------------
    // UPDATE SONG STATUS
    // -------------------------
    public SongResponseDto updateSongStatus(Long id, SongStatusUpdateDto dto) {
        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("Song not found"));

        Song.SongStatus newStatus = dto.getStatus();
        Song.SongStatus currentStatus = song.getStatus();

        // Validate status transition
        validateStatusTransition(currentStatus, newStatus);

        song.setStatus(newStatus);
        song.setUpdatedAt(java.time.LocalDateTime.now());

        Song updatedSong = songRepository.save(song);
        return songMapper.toDto(updatedSong);
    }

    public LyricsResponseDto createLyrics(Long songId, LyricsCreateDto dto) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));
        if (song.getLyrics() != null) {
            throw new IllegalStateException("Lyrics already exist for this song");
        }

        Lyrics lyrics = lyricsMapper.toEntity(dto);
        lyrics.setSong(song);
        lyrics.setVersion(1);
        song.setLyrics(lyrics);
        song.setUpdatedAt(java.time.LocalDateTime.now());

        songRepository.save(song);
        return lyricsMapper.toDto(lyrics);
    }

    private void validateStatusTransition(Song.SongStatus currentStatus, Song.SongStatus newStatus) {
        if (currentStatus == newStatus) {
            throw new IllegalArgumentException("Song is already in status: " + currentStatus);
        }

        switch (currentStatus) {
            case DRAFT:
                if (newStatus != Song.SongStatus.IN_PROGRESS && newStatus != Song.SongStatus.ARCHIVED) {
                    throw new IllegalArgumentException("Invalid transition from DRAFT to " + newStatus + ". Valid transitions: IN_PROGRESS, ARCHIVED");
                }
                break;
            case IN_PROGRESS:
                if (newStatus != Song.SongStatus.COMPLETED && newStatus != Song.SongStatus.ARCHIVED) {
                    throw new IllegalArgumentException("Invalid transition from IN_PROGRESS to " + newStatus + ". Valid transitions: COMPLETED, ARCHIVED");
                }
                break;
            case COMPLETED:
                if (newStatus != Song.SongStatus.ARCHIVED) {
                    throw new IllegalArgumentException("Invalid transition from COMPLETED to " + newStatus + ". Valid transition: ARCHIVED");
                }
                break;
            case ARCHIVED:
                if (newStatus != Song.SongStatus.DRAFT) {
                    throw new IllegalArgumentException("Invalid transition from ARCHIVED to " + newStatus + ". Valid transition: DRAFT");
                }
                break;
        }
    }

    public void deleteSong(Long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("Song not found"));
        songRepository.delete(song);
    }
}