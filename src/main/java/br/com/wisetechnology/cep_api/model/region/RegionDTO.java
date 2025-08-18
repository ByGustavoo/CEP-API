package br.com.wisetechnology.cep_api.model.region;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegionDTO {

    @NotNull
    private String sigla;

    @NotNull
    private String nome;

}