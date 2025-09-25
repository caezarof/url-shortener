package br.com.url_shortener.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Url Shortening API")
                        .version("1.0.0")
                        .description("API documentation")
                        .contact(new Contact()
                                .name("Renato César")
                                .email("renatocesardof@gmail.com")));
    }
}
