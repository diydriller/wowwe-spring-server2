package com.project.wowwe.controller;

import com.project.wowwe.common.exception.BaseException;
import com.project.wowwe.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.project.wowwe.common.response.BaseResponseStatus.VIDEO_ERROR;

@Controller
@AllArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/video/{videoId}")
    public ResponseEntity<ResourceRegion> sendVideo(
            @PathVariable Long videoId, @RequestHeader HttpHeaders headers){
        try {
            return videoService.sendVideo(videoId, headers);
        }
        catch(IOException ex) {
            throw new BaseException(VIDEO_ERROR);
        }
    }



}
