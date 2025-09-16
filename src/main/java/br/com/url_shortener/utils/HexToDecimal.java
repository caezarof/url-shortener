package br.com.url_shortener.utils;

import java.math.BigInteger;

public class HexToDecimal {
    private final static int HEXADECIMAL = 16;
    public static BigInteger convert(String hexString){
        return new BigInteger(hexString, HEXADECIMAL);
    }
}
