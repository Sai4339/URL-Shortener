package com.sai.urlshortener.service;

import com.sai.urlshortener.dto.UrlResponse;
import com.sai.urlshortener.dto.UrlStatsResponse;
import com.sai.urlshortener.entity.Url;
import com.sai.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;
@Service
public class UrlService {

    private final UrlRepository urlRepository;

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom random = new SecureRandom();

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponse saveUrl(String originalUrl) {
        Optional<Url> existing = urlRepository.findByOriginalUrl(originalUrl);

        if (existing.isPresent()) {
            Url url = existing.get();
            return new UrlResponse(url.getOriginalUrl(), url.getShortCode());
        }
        String shortCode;

        do {
            shortCode = generateShortCode();
        } while (urlRepository.existsByShortCode(shortCode));

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        urlRepository.save(url);
        return new UrlResponse(url.getOriginalUrl(), url.getShortCode());
    }

    private String generateShortCode() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }
    public Url getOriginalUrl(String shortCode) {

        Url url = urlRepository.findByShortCode(shortCode).orElse(null);

        if (url != null) {
            url.setClickCount(url.getClickCount() + 1);
            urlRepository.save(url);
        }

        return url;
    }
    public UrlStatsResponse getStats(String shortCode) {

        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        return new UrlStatsResponse(
                url.getOriginalUrl(),
                url.getShortCode(),
                url.getClickCount(),
                url.getCreatedAt()
        );
    }
}
