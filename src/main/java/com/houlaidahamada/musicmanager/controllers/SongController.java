package com.houlaidahamada.musicmanager.controllers;

import com.houlaidahamada.musicmanager.entities.Author;
import com.houlaidahamada.musicmanager.entities.Song;
import com.houlaidahamada.musicmanager.repositories.SongRepository;
import com.houlaidahamada.musicmanager.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    private final SongService songService;

    public SongController(SongService songService){
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }

    @GetMapping("/{id}")
    public Song getSong(@PathVariable Long id){
        return songRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Song createSong(@RequestBody Song song){
        return songRepository.save(song);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song songDetails){
        Song song = songRepository.findById(id).orElse(null);
        if(song != null) {
            song.setTitle(songDetails.getTitle());
            song.setDescription(songDetails.getDescription());
            song.setCompleted((songDetails.isCompleted()));

            return songRepository.save(song);
        }
        return null;
    }

    @PostMapping("/{songId}/authors/{authorId}")
    public Song addAuthorToSong(@PathVariable Long songId, @PathVariable Long authorId) {
        return songService.addAuthorToSong(songId, authorId);
    }

    @DeleteMapping("/{songId}/authors/{authorId}")
    public Song removeAuthorFromSong(@PathVariable Long songId, @PathVariable Long authorId) {
        return songService.removeAuthorFromSong(songId,authorId);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id){
        songRepository.deleteById(id);
    }

    @GetMapping("/completed")
    public List<Song> getCompletedSongs() {
        return songRepository.findByCompleted(true);
    }
}
