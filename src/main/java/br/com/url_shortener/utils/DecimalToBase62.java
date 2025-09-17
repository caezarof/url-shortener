package br.com.url_shortener.utils;

import java.math.BigInteger;

public class DecimalToBase62 {
    private static final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final BigInteger BASE = BigInteger.valueOf(62);
    private static final int MAX_BASE62_LENGTH = 11;

    public static String convert(BigInteger decimal){
        if (decimal == null){
            throw new IllegalArgumentException("Decimal value cannot be null.");
        }
        if (decimal.compareTo(BigInteger.ZERO) < 0){
            throw new IllegalArgumentException("Decimal value cannot be negative.");
        }
        if (decimal.equals(BigInteger.ZERO)) {
            return "0";
        }

        StringBuilder base62 = new StringBuilder(MAX_BASE62_LENGTH);
        BigInteger currentValue = decimal;

        while (currentValue.compareTo(BigInteger.ZERO) > 0){
            BigInteger[] quotientAndRemainder = currentValue.divideAndRemainder(BASE);
            int remainder = quotientAndRemainder[1].intValue();

            if (remainder < 0 || remainder >= BASE62_CHARS.length()){
                throw new ArithmeticException("Invalid remainder calculated during base62 conversion");
            }

            base62.append(BASE62_CHARS.charAt(remainder));
            currentValue = quotientAndRemainder[0];
        }
        return base62.reverse().toString();
    }
}
