package com.prova_java.aplicacao.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class GenericMapper{

    @Autowired
    private ModelMapper mapper;

    /**
     * Retorna as configurações do ModelMapper
     * @return
     */
    public org.modelmapper.config.Configuration getConfiguration() {
        return mapper.getConfiguration();
    }

    /**
     * Copia os dados de um objeto para outro
     * @param origem
     * @param destino
     * @param <O>
     * @param <D>
     */
    public <O, D> void map(O origem, D destino){
        mapper.map(origem, destino);
    }

    /**
     * Copia os dados de um objeto para uma nova instância de outro
     * @param origem
     * @param dClass
     * @param <O>
     * @param <D>
     */
    public <O, D> D map(O origem, Class<D> dClass){
        return mapper.map(origem, dClass);
    }

    /**
     * Copia uma lista de objetos origem para uma nova lista de objetos destino
     * @param origem
     * @param dClass
     * @return
     * @param <O>
     * @param <D>
     */
    public <O, D> List<D> map(Collection<O> origem, Class<D> dClass){
        return origem.stream()
                .map(o -> mapper.map(o, dClass))
                .toList();
    }


}
