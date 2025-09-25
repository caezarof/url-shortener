package br.com.url_shortener.url;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@Tag(name = "Urls", description = "API for url shortening service.")
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service){
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<CreatedShortenUrlDTO> shortenUrl(
            @RequestBody @Valid GenerateUrlDTO generateUrlDto, UriComponentsBuilder uriBuilder){
        CreatedShortenUrlDTO shortenUrl = service.create(generateUrlDto);
        URI uri = uriBuilder.path("/{shortcode}").buildAndExpand(shortenUrl.shortCode()).toUri();
        return ResponseEntity.created(uri).body(shortenUrl);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = service.getOriginalUrlAndIncrementAccess(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
