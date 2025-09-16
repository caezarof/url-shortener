package br.com.url_shortener.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringToMD5Test {

    @Test
    public void encodeTest(){
        String original = "TestCase";
        String expected = "c9aba2c3e60126169e80e9a26ba273c1";
        String result = StringToMD5.encode(original);
        assertEquals(expected, result);
    }
    @Test
    public void encodeWith8Bytes(){
        String original = "TestCase";
        String expected = "c9aba2c3e6012616";
        String result = StringToMD5.encode(original, 8);
        assertEquals(expected, result);
    }
}