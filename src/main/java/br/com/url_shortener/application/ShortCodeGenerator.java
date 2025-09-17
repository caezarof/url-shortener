package br.com.url_shortener.application;

import br.com.url_shortener.exception.ShortCodeGenerationException;
import br.com.url_shortener.utils.DecimalToBase62;
import br.com.url_shortener.utils.HexToDecimal;
import br.com.url_shortener.utils.StringToMD5;
import br.com.url_shortener.utils.TextTimestampConcatenator;

import java.math.BigInteger;

public class ShortCodeGenerator {
    private static final int DEFAULT_TRUNCATED_BYTES = 6;

    public static String generate(String originalUrl) {
        if (originalUrl == null || originalUrl.trim().isEmpty()){
            throw new IllegalArgumentException("Url cannot be null.");
        }
        try{
            String urlTimestamp = TextTimestampConcatenator.concatenate(originalUrl);
            String md5Hash = StringToMD5.encode(urlTimestamp, DEFAULT_TRUNCATED_BYTES);
            BigInteger decimal = HexToDecimal.convert(md5Hash);
            return DecimalToBase62.convert(decimal);
        }catch (IllegalArgumentException e){
            throw new ShortCodeGenerationException(
                    "Invalid input for short code generation: " + e.getMessage(), e);
        }catch (IllegalStateException e){
            throw new ShortCodeGenerationException(
                    "System error during short code generation: " + e.getMessage(), e);
        }
    }
}
