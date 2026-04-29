package com.houlaidahamada.musicmanager.dto.song;

public class SongUpdateDto {
    private String title;
    private String description;
    private boolean completed;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
}
