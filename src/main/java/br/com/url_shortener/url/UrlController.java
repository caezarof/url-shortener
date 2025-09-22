package br.com.url_shortener.url;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service){
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<CreatedShortenUrlDTO> shortenUrl(
            @RequestBody @Valid GenerateUrlDTO generateUrlDto, UriComponentsBuilder uriBuilder){
        CreatedShortenUrlDTO shortenUrl = service.create(generateUrlDto);
        URI uri = uriBuilder.path("/urls/{id}").buildAndExpand(shortenUrl.id()).toUri();
        return ResponseEntity.created(uri).body(shortenUrl);
    }
}
