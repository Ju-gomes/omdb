package com.prova_java.modulos.filme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;
}
