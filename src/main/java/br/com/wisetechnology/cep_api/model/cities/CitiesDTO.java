package br.com.wisetechnology.cep_api.model.cities;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CitiesDTO {

    @NotNull
    private String nome;

    @NotNull
    @JsonAlias("codigo_ibge")
    private Integer codigoIbge;

}