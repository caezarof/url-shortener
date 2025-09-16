package br.com.url_shortener.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortCodeGeneratorTest {

    @Test
    void generate(){
        String url = "https://youtu.be/dQw4w9WgXcQ?list=RDdQw4w9WgXcQ";
        String expected = "Ubjb2RoW";
        String actual = ShortCodeGenerator.generate(url);

        assertEquals(expected, actual);
    }
}