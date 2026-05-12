package com.houlaidahamada.musicmanager.specification;

import org.springframework.data.jpa.domain.Specification;

import com.houlaidahamada.musicmanager.entity.Song;

import jakarta.persistence.criteria.Join;

public class SongSpecification {

    public static Specification<Song> hasStatus(Song.SongStatus status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Song> hasThemeId(Long themeId) {
        return (root, query, cb) -> {
            if (themeId == null) return null;

            Join<Object, Object> themes = root.join("themes");
            return cb.equal(themes.get("id"), themeId);
        };
    }
}
