package br.com.url_shortener.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

class HexToDecimalTest {
    @Test
    public void convert() throws NoSuchAlgorithmException {
        String hexString = StringToMD5.encode("TestCase");
        BigInteger hexToDecimal = HexToDecimal.convert(hexString);

        System.out.println(hexToDecimal + " // " + hexToDecimal.bitLength());
    }

    @Test
    public void convert1() throws NoSuchAlgorithmException {
        String hexString = StringToMD5.encode("TestCase", 8);
        BigInteger hexToDecimal = HexToDecimal.convert(hexString);

        System.out.println(hexToDecimal + " // " + hexToDecimal.bitLength());
    }
}