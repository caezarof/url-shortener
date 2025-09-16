package br.com.url_shortener.exception;

public class ShortCodeGenerationException extends RuntimeException {
    public ShortCodeGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
