package br.com.url_shortener.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTimestampConcatenatorTest {

    @Test
    void concatenate_ShouldReturnStringStartingWithText() {
        String text = "https://www.example.com";
        String result = TextTimestampConcatenator.concatenate(text);
        assertTrue(result.startsWith(text));
    }

    @Test
    void concatenate_ShouldReturnStringLongerThanText() {
        String text = "https://www.example.com";
        String result = TextTimestampConcatenator.concatenate(text);
        assertTrue(result.length() > text.length());
    }

    @Test
    void concatenate_ShouldReturnDifferentStringsForSameText() {
        String text = "https://www.example.com";
        String firstResult = TextTimestampConcatenator.concatenate(text);
        String secondResult = TextTimestampConcatenator.concatenate(text);
        assertNotEquals(firstResult, secondResult);
    }
}