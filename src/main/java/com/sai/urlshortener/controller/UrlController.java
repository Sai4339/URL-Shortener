package com.sai.urlshortener.controller;
import com.sai.urlshortener.dto.UrlResponse;
import com.sai.urlshortener.entity.Url;
import com.sai.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sai.urlshortener.dto.UrlRequest;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sai.urlshortener.dto.UrlStatsResponse;
@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest request) {

        UrlResponse response = urlService.saveUrl(request.getOriginalUrl());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/stats/{shortCode}")
    public ResponseEntity<UrlStatsResponse> getStats(
            @PathVariable String shortCode){

        return ResponseEntity.ok(urlService.getStats(shortCode));
    }
}
