package br.com.url_shortener.url;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record GenerateUrlDTO(@NotNull @NotBlank @URL String originalUrl) {
}
