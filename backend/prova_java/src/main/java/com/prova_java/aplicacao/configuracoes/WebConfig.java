package com.prova_java.aplicacao.configuracoes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${cors}")
    private boolean cors;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        if("dev".equals(profile) || cors == true){
            corsRegistry
                    .addMapping("/**")
                    //"POST", "PUT", "DELETE", "PATCH", "OPTIONS"
                    .allowedMethods("HEAD", "GET");
        }
    }

}
