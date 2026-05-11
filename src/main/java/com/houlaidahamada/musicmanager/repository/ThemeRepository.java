package com.houlaidahamada.musicmanager.repository;

import com.houlaidahamada.musicmanager.dto.theme.ThemeResponseDto;
import com.houlaidahamada.musicmanager.entity.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Optional<Theme> findByName(String name);

    @Query(
            value = "select new com.houlaidahamada.musicmanager.dto.theme.ThemeResponseDto(t.id, t.name, t.description, t.color, count(s)) " +
                    "from Theme t left join t.songs s group by t.id, t.name, t.description, t.color",
            countQuery = "select count(t) from Theme t"
    )
    Page<ThemeResponseDto> findAllWithSongCount(Pageable pageable);
}