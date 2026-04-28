package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.AuthorDTO;
import com.houlaidahamada.musicmanager.dto.SongDTO;
import com.houlaidahamada.musicmanager.service.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService){
        this.songService = songService;
    }

    @GetMapping
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public SongDTO getSong(@PathVariable Long id){
        return songService.getSongById(id);
    }

    @PostMapping
    public SongDTO createSong(@RequestBody SongDTO songDTO){
        return songService.createSong(songDTO);
    }

    @PutMapping("/{id}")
    public SongDTO updateSong(@PathVariable Long id, @RequestBody SongDTO songDTO){
        return songService.updateSong(id, songDTO);
    }

    @PostMapping("/{songId}/authors/{authorId}")
    public SongDTO addAuthorToSong(@PathVariable Long songId, @PathVariable Long authorId) {
        return songService.addAuthorToSong(songId, authorId);
    }

    @DeleteMapping("/{songId}/authors/{authorId}")
    public SongDTO removeAuthorFromSong(@PathVariable Long songId, @PathVariable Long authorId) {
        return songService.removeAuthorFromSong(songId, authorId);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id){
        songService.deleteSong(id);
    }

    @GetMapping("/completed")
    public List<SongDTO> getCompletedSongs() {
        return songService.getCompletedSongs();
    }

    @GetMapping("/search")
    public List<SongDTO> searchSongs(@RequestParam String keyword) {
        return songService.searchSongs(keyword);
    }
}