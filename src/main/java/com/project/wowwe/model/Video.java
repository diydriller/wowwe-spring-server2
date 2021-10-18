package com.project.wowwe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
    private Long id;
    private String title;
    private String description;
    private String thumnailImg;
    private Double duration;
    private LocalDateTime createdAt;
}
