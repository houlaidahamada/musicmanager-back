package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.song.SongCreateDto;
import com.houlaidahamada.musicmanager.dto.song.SongCreateResponseDto;
import com.houlaidahamada.musicmanager.dto.song.SongResponseDto;
import com.houlaidahamada.musicmanager.entity.Song;
import com.houlaidahamada.musicmanager.service.SongService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(required = false) Song.SongStatus status
    ) {
        return songService.getSongs(pageable, status);
    }

    @GetMapping("/{id}")
    public SongResponseDto getSong(@PathVariable Long id){
        return songService.getSongById(id);
    }

    @PostMapping
    public SongCreateResponseDto createSong(@RequestBody SongCreateDto songCreateDto){
        return songService.createSong(songCreateDto);
    }

    // @PutMapping("/{id}")
    // public SongResponseDto updateSong(@PathVariable Long id, @RequestBody SongUpdateDto songUpdateDto){
    //     return songService.updateSong(id, songUpdateDto);
    // }

    // @PostMapping("/{songId}/authors/{authorId}")
    // public SongResponseDto addAuthorToSong(@PathVariable Long songId, @PathVariable Long authorId) {
    //     return songService.addAuthorToSong(songId, authorId);
    // }

    // @DeleteMapping("/{songId}/authors/{authorId}")
    // public SongResponseDto removeAuthorFromSong(@PathVariable Long songId, @PathVariable Long authorId) {
    //     return songService.removeAuthorFromSong(songId, authorId);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteSong(@PathVariable Long id){
    //     songService.deleteSong(id);
    // }

//    @GetMapping("/completed")
//    public List<SongResponseDto> getCompletedSongs() {
//        return songService.getCompletedSongs();
//    }
//
//    @GetMapping("/search")
//    public List<SongResponseDto> searchSongs(@RequestParam String keyword) {
//        return songService.searchSongs(keyword);
//    }
}