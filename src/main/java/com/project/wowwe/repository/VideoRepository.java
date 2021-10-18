package com.project.wowwe.repository;

import com.project.wowwe.model.Video;

import java.util.Optional;

public interface VideoRepository {
    Optional<Video> findById(Long id);
    int save(Video video);
}
