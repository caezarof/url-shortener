package br.com.url_shortener.url;

import br.com.url_shortener.application.ShortCodeGenerator;
import br.com.url_shortener.exception.ShortCodeGenerationException;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlEntityRepository repository;

    public UrlService(UrlEntityRepository repository){
        this.repository = repository;
    }


    public UrlEntity create(GenerateUrlDTO dto){
        createValidation(dto);

        String shortCode = null;
        int MAXIMUM_ATTEMPTS = 3;

        while (MAXIMUM_ATTEMPTS > 0){
            try {
                String shortCodeTry = ShortCodeGenerator.generate(dto.originalUrl());
                if (!repository.existsByShortCode(shortCodeTry)){
                    shortCode = shortCodeTry;
                    break;
                }
            } catch (RuntimeException e){
                throw new ShortCodeGenerationException("Failed to generate short code.", e.getCause());
            }
            MAXIMUM_ATTEMPTS--;
        }

        if (shortCode == null){
            throw new ShortCodeGenerationException("Could not generate unique short code after 3 attempts. Please try again.");
        }

        UrlEntity shortUrl = new UrlEntity(dto.originalUrl(), shortCode);

        return repository.save(shortUrl);
    }

    private void createValidation(GenerateUrlDTO dto){
        if (dto == null || dto.originalUrl() == null || dto.originalUrl().trim().isEmpty()){
            throw new IllegalArgumentException("Original url cannot be null or empty.");
        }
        if (dto.originalUrl().length() > 2048){
            throw new IllegalArgumentException("Url exceeds maximum length.");
        }
    }
}
