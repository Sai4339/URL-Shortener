package com.sai.urlshortener.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequest {

    @NotBlank(message = "URL cannot be empty")
    private String originalUrl;
}
