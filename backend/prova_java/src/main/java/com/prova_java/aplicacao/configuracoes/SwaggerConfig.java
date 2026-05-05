package com.prova_java.aplicacao.configuracoes;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
public class SwaggerConfig {

    @Value("$app.version")
    private String appVersion;

    @Value("$app.name")
    private String appName;

    @Value("$app.description")
    private String appDescription;

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .version(appVersion)
                        .description(appDescription)
                        .contact(new Contact()
                                .name("Juliano Gomes")
                                .email("julianocg2013@gmail.com")));
    }

}
