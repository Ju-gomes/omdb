package com.prova_java.modulos.filme.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prova_java.modulos.filme.model.FilmeFiltro;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilmeFiltroResponse {

    @JsonProperty("Search")
    private List<FilmeFiltro> search;

    @JsonProperty("Response")
    private String response;

    @JsonProperty("Error")
    private String error;

    @JsonProperty("totalResults")
    private String totalResults;

    @JsonProperty("CurrentPage")
    private String currentPage;
}
