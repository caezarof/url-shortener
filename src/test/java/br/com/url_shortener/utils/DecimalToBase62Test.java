package br.com.url_shortener.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class DecimalToBase62Test {

    @Test
    void shouldConvertZeroToZero() {
        assertEquals("0", DecimalToBase62.convert(BigInteger.ZERO));
    }

    @Test
    void shouldConvertOneToFirstCharacter() {
        assertEquals("1", DecimalToBase62.convert(BigInteger.ONE));
    }

    @Test
    void shouldConvert61ToLastCharacter() {
        assertEquals("z", DecimalToBase62.convert(BigInteger.valueOf(61)));
    }

    @Test
    void shouldConvert62To10() {
        assertEquals("10", DecimalToBase62.convert(BigInteger.valueOf(62)));
    }

    @Test
    void shouldHandleLargeNumbers() {
        BigInteger largeNumber = new BigInteger("12345678901234567890");
        assertDoesNotThrow(() -> DecimalToBase62.convert(largeNumber));
    }

    @Test
    void shouldThrowExceptionForNullInput() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> DecimalToBase62.convert(null));
        assertEquals("Decimal value cannot be null.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionForNegativeNumbers() {
        IllegalArgumentException e =assertThrows(IllegalArgumentException.class,
                () -> DecimalToBase62.convert(BigInteger.valueOf(-1)));
        assertEquals("Decimal value cannot be negative.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1, 10, 61, 62, 100, 1000, 123456789})
    void shouldReversiblyConvert(long value) {
        String base62 = DecimalToBase62.convert(BigInteger.valueOf(value));
        assertNotNull(base62);
        assertFalse(base62.isEmpty());
        assertTrue(base62.matches("[0-9A-Za-z]+"));
    }

    @Test
    void shouldProduceExpectedSequence() {
        assertEquals("0", DecimalToBase62.convert(BigInteger.valueOf(0)));
        assertEquals("1", DecimalToBase62.convert(BigInteger.valueOf(1)));
        assertEquals("A", DecimalToBase62.convert(BigInteger.valueOf(10)));
        assertEquals("Z", DecimalToBase62.convert(BigInteger.valueOf(35)));
        assertEquals("a", DecimalToBase62.convert(BigInteger.valueOf(36)));
        assertEquals("z", DecimalToBase62.convert(BigInteger.valueOf(61)));
        assertEquals("10", DecimalToBase62.convert(BigInteger.valueOf(62)));
        assertEquals("11", DecimalToBase62.convert(BigInteger.valueOf(63)));
    }
}