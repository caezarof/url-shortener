package br.com.url_shortener.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringToMD5 {

    private static final int MD5_BYTE_LENGTH = 16;

    public static String encode(String text){
        return encode(text, MD5_BYTE_LENGTH);
    }

    //Returns a truncated value based in the bytes you want to consider
    //If no value is passed, we will be considerate the full hexadecimal value
    public static String encode(String text, int bytes) {
        validateInput(text);
        validateBytes(bytes);

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);

            digest.update(textBytes);
            byte[] hashBytes = digest.digest();

            return bytesToHex(hashBytes, bytes);
        } catch (NoSuchAlgorithmException e){
            throw new IllegalStateException("MD5 algorithm not available.", e);
        }
    }

    private static String bytesToHex(byte[] bytes, int length) {
        int bytesToProcess = Math.min(length, bytes.length);
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytesToProcess; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private static void validateInput(String text){
        if (text == null){
            throw new IllegalArgumentException("Text cannot be null.");
        }
        if (text.trim().isEmpty()){
            throw new IllegalArgumentException("Text cannot be empty or whitespace only.");
        }
    }

    private static void validateBytes(int bytes){
        if (bytes < 1 || bytes > MD5_BYTE_LENGTH){
            throw new IllegalArgumentException(
                    String.format("Bytes must be between 1 and %d.", MD5_BYTE_LENGTH)
            );
        }
    }
}
