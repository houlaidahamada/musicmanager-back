package com.houlaidahamada.musicmanager.controllers;

import com.houlaidahamada.musicmanager.entities.Song;
import com.houlaidahamada.musicmanager.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

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

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id){
        songRepository.deleteById(id);
    }

    @GetMapping("/completed")
    public List<Song> getCompletedSongs() {
        return songRepository.findByCompleted(true);
    }
}
