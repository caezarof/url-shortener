package br.com.url_shortener.utils;

import java.math.BigInteger;

public class DecimalToBase62 {
    private static final String BASE62_CHARS = """
            0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
            """;

    public static String convert(BigInteger decimal){
        if (decimal.equals(BigInteger.ZERO)) {
            return "0";
        }

        StringBuilder base62 = new StringBuilder();
        BigInteger base = BigInteger.valueOf(62);

        while (decimal.compareTo(BigInteger.ZERO) > 0){
            BigInteger[] quotientAndRemainder = decimal.divideAndRemainder(base);
            int remainder = quotientAndRemainder[1].intValue();
            base62.insert(0, BASE62_CHARS.charAt(remainder));
            decimal = quotientAndRemainder[0];
        }
        return base62.toString();
    }
}
