package br.com.url_shortener.utils;

import java.time.Instant;

public class TextTimestampConcatenator {
    public static String concatenate(String text) {
        String timesTamp = String.valueOf(Instant.now().getNano());
        return text+timesTamp;
    }
}
