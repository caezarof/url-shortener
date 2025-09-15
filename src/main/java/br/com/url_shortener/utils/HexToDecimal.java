package br.com.url_shortener.utils;

import java.math.BigInteger;

public class HexToDecimal {
    public static BigInteger convert(String hexString){
        return new BigInteger(hexString, 16);
    }
}
