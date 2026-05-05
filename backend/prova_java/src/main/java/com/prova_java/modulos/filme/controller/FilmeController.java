package com.prova_java.modulos.filme.controller;

import com.prova_java.aplicacao.util.GenericMapper;
import com.prova_java.modulos.filme.model.Filme;
import com.prova_java.modulos.filme.model.dto.response.FilmeFiltroResponse;
import com.prova_java.modulos.filme.model.dto.response.FilmeResponse;
import com.prova_java.modulos.filme.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/omdb")
@Tag(name = "OMDd Catálogo de filmes e séries", description = "Api para consulta de filmes e séries.")
public class FilmeController {

    private final FilmeService service;
    private final GenericMapper mapper;

    public FilmeController(FilmeService service, GenericMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Consulta filme através de seu ID.")
    @GetMapping("/filme/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmeResponse buscarFilmeId(@PathVariable String id) {
        Filme filme = service.buscarPorId(id);
        return mapper.map(filme, FilmeResponse.class);
    }

    @Operation(summary = "Consulta filme através de seu título e outros dados.")
    @GetMapping("/filtro")
    @ResponseStatus(HttpStatus.OK)
    public FilmeFiltroResponse buscarFilmesFiltro(
            @RequestParam String title,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "1") Integer page) {
        return service.buscarPorFiltro(title, year, type, page);
    }
}