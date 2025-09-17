package br.com.url_shortener.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringToMD5Test {

    private static final int MD5_BYTE_LENGTH = 16;

    @Test
    void shouldReturnMd5StringWithValidTextAndNoBytes(){
        String text = "AAA";

        String expected = "e1faffb3e614e6c2fba74296962386b7";
        String actual = StringToMD5.encode(text);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMd5StringWithValidTextAnd6Bytes(){
        String text = "AAA";
        int bytes = 6;

        String expected = "e1faffb3e614";
        String actual = StringToMD5.encode(text,bytes);

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTextNull(){
        String text = null;

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> StringToMD5.encode(text));

        assertEquals("Text cannot be null.", e.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTextEmpty(){
        String text = "";

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> StringToMD5.encode(text));

        assertEquals("Text cannot be empty or whitespace only.", e.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTextBytesLessThan1(){
        String text = "Abc";
        int bytes = 0;

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> StringToMD5.encode(text, bytes));

        assertEquals(String.format("Bytes must be between 1 and %d.", MD5_BYTE_LENGTH), e.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTextBytesGreaterThan16(){
        String text = "Abc";
        int bytes = 17;

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> StringToMD5.encode(text, bytes));

        assertEquals(String.format("Bytes must be between 1 and %d.", MD5_BYTE_LENGTH), e.getMessage());
    }

    @Test
    void encodeWith16BytesShouldEqualEncodeWithoutBytes() {
        String text = "AAA";
        String fullHash = StringToMD5.encode(text);
        String hash16Bytes = StringToMD5.encode(text, 16);

        assertEquals(fullHash, hash16Bytes);
    }

    @Test
    void encodeWith1ByteShouldReturnFirst2HexChars() {
        String text = "AAA";
        String hash1Byte = StringToMD5.encode(text, 1);
        String fullHash = StringToMD5.encode(text);

        assertEquals(fullHash.substring(0, 2), hash1Byte);
    }
}