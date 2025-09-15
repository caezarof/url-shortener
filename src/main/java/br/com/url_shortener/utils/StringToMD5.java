package br.com.url_shortener.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringToMD5 {

    public static String encode(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] originalUrlBytes = text.getBytes(StandardCharsets.UTF_8);

        digest.update(originalUrlBytes, 0, originalUrlBytes.length);
        byte[] hashBytes = digest.digest();

        StringBuilder hexString = new StringBuilder();

        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    //Returns a truncated value based in the bytes you want to consider
    public static String encode(String text, int bytes) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] originalUrlBytes = text.getBytes(StandardCharsets.UTF_8);

        digest.update(originalUrlBytes, 0, originalUrlBytes.length);
        byte[] hashBytes = digest.digest();

        int bytesToProcess = Math.min(bytes, hashBytes.length);
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytesToProcess; i++){
            byte b = hashBytes[i];

            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1){
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
