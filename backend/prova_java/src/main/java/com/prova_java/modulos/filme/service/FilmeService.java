package com.prova_java.modulos.filme.service;

import com.prova_java.aplicacao.exceptions.DomainException;
import com.prova_java.aplicacao.util.GenericMapper;
import com.prova_java.modulos.filme.model.Filme;
import com.prova_java.modulos.filme.model.dto.response.FilmeFiltroResponse;
import com.prova_java.modulos.filme.model.dto.response.FilmeResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class FilmeService {

   private final GenericMapper mapper;
   private final RestTemplate restTemplate;

    public FilmeService(GenericMapper mapper, RestTemplate restTemplate) {
        this.mapper = mapper;
        this.restTemplate = restTemplate;
    }

    @Value("${omdb.api.key}")
    private String key;

    @Value("${omdb.api.url}")
    private String url;

    @Cacheable(value = "omdbById", key = "#id")
    public Filme buscarPorId(String id){
        URI uri = URI.create(url +
                "?i=" + id +
                "&apikey=" + key);

        FilmeResponse response = restTemplate.getForObject(uri, FilmeResponse.class);
        if(response.getResponse().equalsIgnoreCase("False")) {
            throw new DomainException("Filme / série não encontrado para ID: " + id);
        }
        if (response == null ) {
            throw new DomainException("Filme não encontrado para ID: " + id);
        }

        return mapper.map(response, Filme.class);
    }

    /**
     * Método busca filmes de acordo com o filtro enviado.
     * O objeto retornado é diferente de FilmeResponse, pois o retorno da api é diferente.
     * @param title
     * @param year
     * @param type
     * @param page
     * @return
     */
    @Cacheable(value = "omdbSearch", unless = "#result.response == 'False'")
     public FilmeFiltroResponse buscarPorFiltro(String title, String year, String type, Integer page){
        StringBuilder stringBuilder = new StringBuilder(url)
                .append("?apikey=").append(key)
                .append("&s=").append(title);

        if (page == null || page < 1) {
            page = 1;
        }

        stringBuilder.append("&page=").append(page);

        if(StringUtils.hasText(year)){
            stringBuilder.append("&y=").append(year);
        }
        if(StringUtils.hasText(type)){
            stringBuilder.append("&type=").append(type);
        }

        URI uri = URI.create(stringBuilder.toString());

        FilmeFiltroResponse response = restTemplate.getForObject(uri, FilmeFiltroResponse.class);
        response.setCurrentPage(page.toString());

        if(response.getResponse().equalsIgnoreCase("False")) {
            throw new DomainException("Nenhum filme / série encontrado para: " + title);
        }
        if(response == null){
            throw new DomainException("Nenhum resultado encontrado para: " + title);
        }

        return response;


    }


}