package com.prova_java.prova_java;

import com.prova_java.aplicacao.exceptions.DomainException;
import com.prova_java.aplicacao.util.GenericMapper;
import com.prova_java.modulos.filme.model.Filme;
import com.prova_java.modulos.filme.model.dto.response.FilmeFiltroResponse;
import com.prova_java.modulos.filme.model.dto.response.FilmeResponse;
import com.prova_java.modulos.filme.service.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.eq;

import java.net.URI;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmeServiceTest {

    @Mock
    private GenericMapper mapper;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FilmeService service;

    private final String url = "http://mock-api.com";
    private final String key = "123";

    @BeforeEach
    void setup() throws Exception{
        ReflectionTestUtils.setField(service, "url", url);
        ReflectionTestUtils.setField(service, "key", key);
    }

    //Deve buscar filme por filtro
    @Test
    void deveBuscarFilmePorIdComSucesso() {
        String id = "tt12345";
        FilmeResponse responseMock = new FilmeResponse();
        Filme filmeEsperado = new Filme();
        filmeEsperado.setTitle("Inception");

        when(restTemplate.getForObject(any(URI.class), eq(FilmeResponse.class)))
                .thenReturn(responseMock);
        when(mapper.map(responseMock, Filme.class))
                .thenReturn(filmeEsperado);

        Filme resultado = service.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals("Inception", resultado.getTitle());
        verify(restTemplate, times(1)).getForObject(any(URI.class), eq(FilmeResponse.class));

    }

    @Test
    void deveLancarExcecaoQuandoFilmeNaoEncontrado() {
        String id = "id_invalido";
        when(restTemplate.getForObject(any(URI.class), eq(FilmeResponse.class)))
                .thenReturn(null);

        DomainException exception = assertThrows(DomainException.class, () -> {
            service.buscarPorId(id);
        });

        assertEquals("Filme não encontrado para ID: " + id, exception.getMessage());
    }

    @Test
    void deveBuscarPorFiltroEConfigurarPaginaDefault() {
        String title = "Batman";
        FilmeFiltroResponse mockResponse = new FilmeFiltroResponse();

        when(restTemplate.getForObject(any(URI.class), eq(FilmeFiltroResponse.class)))
                .thenReturn(mockResponse);

        // Passando null na página para testar a lógica do 'if (page == null)'
        FilmeFiltroResponse resultado = service.buscarPorFiltro(title, "2022", "movie", null);

        assertEquals("1", resultado.getCurrentPage());
        verify(restTemplate).getForObject(argThat(uri ->
                uri.toString().contains("s=Batman") && uri.toString().contains("page=1")
        ), eq(FilmeFiltroResponse.class));
    }


}
