package br.com.url_shortener.url;

import br.com.url_shortener.application.ShortCodeGenerator;
import br.com.url_shortener.exception.ShortCodeGenerationException;
import br.com.url_shortener.exception.UrlNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UrlService {
    private static final int MAXIMUM_ATTEMPTS = 3;
    private final UrlEntityRepository repository;
    private final ShortCodeGenerator shortCodeGenerator;
    private static final Logger logger = LoggerFactory.getLogger(UrlService.class);

    public UrlService(UrlEntityRepository repository, ShortCodeGenerator shortCodeGenerator){
        this.repository = repository;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    public String getOriginalUrl(String shortCode) {
        Optional<UrlEntity> result = repository.findByShortCode(shortCode);
        if (result.isPresent()) {
            UrlEntity entity = result.get();
            return entity.getOriginalUrl();
        }
        throw new UrlNotFoundException(shortCode);
    }

    @Transactional
    public CreatedShortenUrlDTO create(GenerateUrlDTO dto){
        createValidation(dto);

        for (int attempt = 0; attempt < MAXIMUM_ATTEMPTS; attempt++){
            String shortCode = shortCodeGenerator.generate(dto.originalUrl());

            if (repository.existsByShortCode(shortCode)){
                continue;
            }

            try {
                UrlEntity shortUrl = new UrlEntity(dto.originalUrl(), shortCode);
                repository.save(shortUrl);
                return new CreatedShortenUrlDTO(shortUrl);
            } catch (DataIntegrityViolationException e){
                logger.debug("Concurrency detected in attempt {}: {}", attempt + 1, e.getMessage());
            }
        }
        throw new ShortCodeGenerationException(
                "Could not generate a unique short code after " + MAXIMUM_ATTEMPTS + " attempts. " +
                        "Please try again.");
    }


    private void createValidation(GenerateUrlDTO dto){
        if (dto == null || dto.originalUrl() == null || dto.originalUrl().trim().isEmpty()){
            throw new IllegalArgumentException("Original url cannot be null or empty.");
        }
        if (dto.originalUrl().length() > 2048){
            throw new IllegalArgumentException("Url exceeds maximum length.");
        }
        if (!dto.originalUrl().startsWith("http://") && !dto.originalUrl().startsWith("https://")) {
            throw new IllegalArgumentException("Url must start with http:// or https://");
        }
    }
}
