package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.lyrics.LyricsCompareResponseDto;
import com.houlaidahamada.musicmanager.dto.lyrics.LyricsCreateDto;
import com.houlaidahamada.musicmanager.dto.lyrics.LyricsResponseDto;
import com.houlaidahamada.musicmanager.dto.lyrics.LyricsUpdateDto;
import com.houlaidahamada.musicmanager.dto.reference.ReferenceCreateDto;
import com.houlaidahamada.musicmanager.dto.reference.ReferenceResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongCreateDto;
import com.houlaidahamada.musicmanager.dto.song.SongCreateResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongStatusUpdateDto;
import com.houlaidahamada.musicmanager.dto.song.SongUpdateDto;
import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.service.SongService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService){
        this.songService = songService;
    }

    @GetMapping
    public Page<SongResponseDto> getSongs(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC, size = 20) Pageable pageable,
            @RequestParam(required = false) Song.SongStatus status,
            @RequestParam(required = false) Long themeId
    ) {
        return songService.getSongs(pageable, status, themeId);
    }

    @GetMapping("/{id}")
    public SongResponseDto getSong(@PathVariable Long id){
        return songService.getSongById(id);
    }

    @PostMapping
    public SongCreateResponseDto createSong(@RequestBody SongCreateDto songCreateDto){
        return songService.createSong(songCreateDto);
    }

    @PutMapping("/{id}")
    public SongResponseDto updateSong(@PathVariable Long id, @Valid @RequestBody SongUpdateDto songUpdateDto){
        return songService.updateSong(id, songUpdateDto);
    }

    @PutMapping("/{id}/status")
    public SongResponseDto updateSongStatus(@PathVariable Long id, @Valid @RequestBody SongStatusUpdateDto statusUpdateDto){
        return songService.updateSongStatus(id, statusUpdateDto);
    }

    @PostMapping("/{id}/lyrics")
    public LyricsResponseDto createSongLyrics(@PathVariable Long id, @Valid @RequestBody LyricsCreateDto lyricsCreateDto){
        return songService.createLyrics(id, lyricsCreateDto);
    }

    @PutMapping("/{id}/lyrics")
    public LyricsResponseDto updateSongLyrics(@PathVariable Long id, @Valid @RequestBody LyricsUpdateDto lyricsUpdateDto){
        return songService.updateLyrics(id, lyricsUpdateDto);
    }

    @GetMapping("/{id}/lyrics/versions")
    public List<LyricsResponseDto> getSongLyricsVersions(@PathVariable Long id){
        return songService.getLyricsVersions(id);
    }

    @PostMapping("/{id}/lyrics/revert/{version}")
    public LyricsResponseDto revertSongLyricsVersion(@PathVariable Long id, @PathVariable Integer version) {
        return songService.revertLyricsVersion(id, version);
    }

    @GetMapping("/{id}/lyrics/versions/compare")
    public LyricsCompareResponseDto compareSongLyricsVersions(
            @PathVariable Long id,
            @RequestParam Integer baseVersion,
            @RequestParam Integer compareVersion
    ) {
        return songService.compareLyricsVersions(id, baseVersion, compareVersion);
    }

    @GetMapping("/{id}/references")
    public List<ReferenceResponseDto> getSongReferences(@PathVariable Long id){
        return songService.getSongReferences(id);
    }

    @PostMapping("/{id}/references")
    public ReferenceResponseDto createSongReference(@PathVariable Long id, @Valid @RequestBody ReferenceCreateDto referenceCreateDto){
        return songService.createSongReference(id, referenceCreateDto);
    }

    @PostMapping("/{songId}/authors/{authorId}")
    public SongResponseDto assignAuthorToSong(@PathVariable Long songId, @PathVariable Long authorId) {
        return songService.assignAuthorToSong(songId, authorId);
    }
    
    @DeleteMapping("/{songId}/authors/{authorId}")
    public SongResponseDto removeAuthorFromSong(@PathVariable Long songId, @PathVariable Long authorId) {
        return songService.removeAuthorFromSong(songId, authorId);
    }

    @PostMapping("/{songId}/themes/{themeId}")
    public SongResponseDto assignThemeToSong(@PathVariable Long songId, @PathVariable Long themeId) {
        return songService.assignThemeToSong(songId, themeId);
    }

    @DeleteMapping("/{songId}/themes/{themeId}")
    public SongResponseDto removeThemeFromSong(@PathVariable Long songId, @PathVariable Long themeId) {
        return songService.removeThemeFromSong(songId, themeId);
    }
    
    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id){
        songService.deleteSong(id);
    }
}