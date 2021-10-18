package com.project.wowwe.service;

import com.project.wowwe.common.exception.BaseException;
import com.project.wowwe.common.response.BaseResponseStatus;
import com.project.wowwe.model.Video;
import com.project.wowwe.repository.VideoRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.project.wowwe.common.response.BaseResponseStatus.VIDEO_ERROR;
import static com.project.wowwe.common.response.BaseResponseStatus.VIDEO_NOT_EXIST;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository=videoRepository;
    }

    @Transactional
    public ResponseEntity<ResourceRegion> sendVideo(Long videoId, HttpHeaders headers) throws BaseException, IOException {

        Video video=videoRepository.findById(videoId).orElseThrow(()-> new BaseException(VIDEO_NOT_EXIST));
        String title=video.getTitle();
        String fileFullPath = "src/main/resources/static/" + title;
        Resource resource = new FileSystemResource(fileFullPath);
        final long chunkSize = 1024 * 1024 * 1;
        long contentLength=resource.contentLength();
        ResourceRegion region;

        try {
            HttpRange httpRange = headers.getRange().stream().findFirst().get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);
            region = new ResourceRegion(resource, start, rangeLength);

        } catch (Exception e) {
            long rangeLength = Long.min(chunkSize, contentLength);
            region = new ResourceRegion(resource, 0, rangeLength);
        }

        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .header("Accept-Ranges", "bytes")
                .eTag(title)
                .body(region);

    }



}
