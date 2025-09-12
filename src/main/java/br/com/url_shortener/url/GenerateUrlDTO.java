package br.com.url_shortener.url;

import jakarta.validation.constraints.NotBlank;

public record GenerateUrlDTO(@NotBlank String originalUrl) {
}
