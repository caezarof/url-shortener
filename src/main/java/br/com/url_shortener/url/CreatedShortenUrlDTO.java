package br.com.url_shortener.url;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record CreatedShortenUrlDTO(Long id, String originalUrl, String shortCode, String createdAt) {

    public CreatedShortenUrlDTO(UrlEntity shortenUrl){
        this(shortenUrl.getId(), shortenUrl.getOriginalUrl(), shortenUrl.getShortCode(),
                formatInstantToSaoPaulo(shortenUrl.getCreatedAt()));
    }

    private static String formatInstantToSaoPaulo(Instant instant) {
        ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")
                .withZone(saoPauloZone);

        return formatter.format(instant);
    }
}
