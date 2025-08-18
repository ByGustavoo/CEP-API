package br.com.wisetechnology.cep_api.model.state;

import br.com.wisetechnology.cep_api.model.region.RegionDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StateDTO {

    @Max(2)
    @NotNull
    private String sigla;

    @Max(20)
    @NotNull
    private String nome;

    @NotNull
    private RegionDTO regiao;

}