package br.com.url_shortener.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

class DecimalToBase62Test {

    @Test
    void convert() throws NoSuchAlgorithmException {
        String md5 = StringToMD5.encode("TestCase", 8);
        BigInteger decimal = HexToDecimal.convert(md5);
        String base62 = DecimalToBase62.convert(decimal);

        System.out.println(md5);
        System.out.println(decimal);
        System.out.println(base62);
    }
}