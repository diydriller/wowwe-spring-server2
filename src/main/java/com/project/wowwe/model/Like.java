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
public class Like {
    private Long id;
    private Long userId;
    private Long videoId;
    private LocalDateTime createdAt;
}
