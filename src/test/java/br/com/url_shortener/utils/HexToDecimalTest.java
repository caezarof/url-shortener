package br.com.url_shortener.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class HexToDecimalTest {
    @Test
    public void convert(){
        String hexString = StringToMD5.encode("TestCase");
        BigInteger hexToDecimal = HexToDecimal.convert(hexString);

        System.out.println(hexToDecimal + " // " + hexToDecimal.bitLength());
    }

    @Test
    public void convert1(){
        String hexString = StringToMD5.encode("TestCase", 8);
        BigInteger hexToDecimal = HexToDecimal.convert(hexString);

        System.out.println(hexToDecimal + " // " + hexToDecimal.bitLength());
    }
}