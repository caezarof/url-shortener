package br.com.url_shortener.url;

import br.com.url_shortener.exception.ShortCodeGenerationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

    @Mock
    private UrlEntityRepository repository;

    @InjectMocks
    private UrlService service;

    @Test
    void createdWithNoFailedAttempts(){
        GenerateUrlDTO dto = new GenerateUrlDTO("https://www.google.com/");

        when(repository.existsByShortCode(anyString()))
                .thenReturn(false);

        UrlEntity expectedEntity = new UrlEntity(dto.originalUrl(), "shortCode");
        when(repository.save(any(UrlEntity.class))).thenReturn(expectedEntity);

        UrlEntity result = service.create(dto);

        verify(repository, times(1)).existsByShortCode(anyString());
        verify(repository, times(1)).save(any(UrlEntity.class));

        assertNotNull(result);
        assertEquals(dto.originalUrl(), result.getOriginalUrl());
        assertNotNull(result.getShortCode());
    }

    @Test
    void createdWithTwoFailedAttempts(){
        GenerateUrlDTO dto = new GenerateUrlDTO("https://www.google.com/");

        when(repository.existsByShortCode(anyString()))
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        UrlEntity expectedEntity = new UrlEntity(dto.originalUrl(), "shortCode");
        when(repository.save(any(UrlEntity.class))).thenReturn(expectedEntity);

        UrlEntity result = service.create(dto);

        verify(repository, times(3)).existsByShortCode(anyString());
        verify(repository, times(1)).save(any(UrlEntity.class));

        assertNotNull(result);
        assertEquals(dto.originalUrl(), result.getOriginalUrl());
        assertNotNull(result.getShortCode());
    }

    @Test
    void createdWithOneFailedAttempt(){
        GenerateUrlDTO dto = new GenerateUrlDTO("https://www.google.com/");

        when(repository.existsByShortCode(anyString()))
                .thenReturn(true)
                .thenReturn(false);

        UrlEntity expectedEntity = new UrlEntity(dto.originalUrl(), "shortCode");
        when(repository.save(any(UrlEntity.class))).thenReturn(expectedEntity);

        UrlEntity result = service.create(dto);

        verify(repository, times(2)).existsByShortCode(anyString());
        verify(repository, times(1)).save(any(UrlEntity.class));

        assertNotNull(result);
        assertEquals(dto.originalUrl(), result.getOriginalUrl());
        assertNotNull(result.getShortCode());
    }

    @Test
    void failedToCreateDueMaximumAttemptsReached(){
        GenerateUrlDTO dto = new GenerateUrlDTO("https://www.google.com/");


        when(repository.existsByShortCode(anyString())).thenReturn(true);

        ShortCodeGenerationException e = assertThrows(
                ShortCodeGenerationException.class,
                () -> service.create(dto)
        );

        System.out.println("Mensagem da exceção: " + e.getMessage());
        assertEquals(
                "Could not generate unique short code after 3 attempts. Please try again.",
                e.getMessage());

        verify(repository, times(3)).existsByShortCode(anyString());
        verify(repository, never()).save(any(UrlEntity.class));
    }

    @Test
    void failedToCreateUrlExceedsMaximumLength(){
        String s = "s".repeat(2049);

        GenerateUrlDTO urlExceedsMaximumLength = new GenerateUrlDTO(s);

        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class, () -> service.create(urlExceedsMaximumLength));
        assertEquals("Url exceeds maximum length.", e.getMessage());
    }

    @Test
    void failedToCreateDueBlankUrlInDTO(){
        GenerateUrlDTO blankUrlDto = new GenerateUrlDTO("");

        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class, () -> service.create(blankUrlDto));

        assertEquals("Original url cannot be null or empty.", e.getMessage());
    }

    @Test
    void failedToCreateDueNullUrlInDTO(){
        GenerateUrlDTO nullUrlDto = new GenerateUrlDTO(null);

        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class, () -> service.create(nullUrlDto));

        assertEquals("Original url cannot be null or empty.", e.getMessage());
    }
    @Test
    void failedToCreateDueNullDTO() {
        GenerateUrlDTO nullDto = null;

        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class, () -> service.create(nullDto));

        assertEquals("Original url cannot be null or empty.", e.getMessage());
    }
}