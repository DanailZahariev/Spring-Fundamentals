package com.example.finalexam.repository;

import com.example.finalexam.model.entity.Song;
import com.example.finalexam.model.entity.enums.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByStyleStyleName(StyleEnum styleEnum);
}
