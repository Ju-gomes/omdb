package com.prova_java.aplicacao.configuracoes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Component
@ConfigurationProperties("omdb.api")
public class OmdbApiConfig {

//    @Value("${omdb.api.key}")
    private String key;

//    @Value("${omdb.api.url}")
    private String url;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
