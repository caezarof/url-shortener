package br.com.url_shortener.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortCodeGeneratorTest {

    private final ShortCodeGenerator shortCodeGenerator = new ShortCodeGenerator();


    @Test
    void shouldThrowExceptionForNullUrl(){
        String nullUrl = null;

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> shortCodeGenerator.generate(nullUrl));

        assertEquals("Url cannot be null.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyString(){
        String emptyUrl = "";

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> shortCodeGenerator.generate(emptyUrl));

        assertEquals("Url cannot be null.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionForWhiteSpaceOnlyString(){
        String whiteSpaceString = "    ";

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> shortCodeGenerator.generate(whiteSpaceString));

        assertEquals("Url cannot be null.", e.getMessage());
    }

    @Test
    void shouldReturnValidShortCodeForValidString(){
        String[] testUrls = {
                "https://www.example.com",
                "https://example.com?query=param&value=test",
                "https://example.com/path/with/slashes",
                "https://example.com:8080/path",
                "https://user:password@example.com",
                "https://example.com/with-umlaut-äöü",
                "https://例子.测试"
        };

        for (String url : testUrls){
            String shortCode = shortCodeGenerator.generate(url);

            assertNotNull(shortCode, "Shortcode should not be null for the url: " + url);
            assertFalse(shortCode.isEmpty(), "Shortcode should not be empty for the url: " + url);
            assertTrue(shortCode.matches("[0-9A-Za-z]+"),
                    "Shortcode must contain only alphanumeric characters for the URL: " + url);
        }
    }

    @Test
    void shouldReturnValidShortCodeForValidUrl(){
        String validUrl = "https://www.example.com";

        String validShortCode = shortCodeGenerator.generate(validUrl);

        assertTrue(validShortCode.matches("[0-9A-Za-z]+"), "Shortcode must contain only alphanumeric characters.");
        assertTrue(validShortCode.length() >= 6 && validShortCode.length() <= 10,
                "Shortcode must be between 6 and 10 characters long.");
        assertNotNull(validShortCode, "Shortcode should not be null");
    }


    @Test
    void shouldReturnDifferentShortCodesForSameUrl() throws InterruptedException {
        String url = "https://www.example.com";

        String shortCode1 = shortCodeGenerator.generate(url);
        Thread.sleep(1);
        String shortCode2 = shortCodeGenerator.generate(url);

        assertNotEquals(shortCode1, shortCode2, "Shortcodes should be different due unique timestamps");
    }

    @Test
    void shouldReturnDifferentShortCodesForDifferentUrl() {
        String url1 = "https://www.example.com";
        String url2 = "https://www.example.com.br";

        String shortCode1 = shortCodeGenerator.generate(url1);
        String shortCode2 = shortCodeGenerator.generate(url2);

        assertNotEquals(shortCode1, shortCode2, "Shortcodes should be differente due being different urls");
    }
}