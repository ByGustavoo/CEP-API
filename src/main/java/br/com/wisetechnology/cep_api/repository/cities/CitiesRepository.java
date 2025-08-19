package br.com.wisetechnology.cep_api.repository.cities;

import br.com.wisetechnology.cep_api.model.cities.CitiesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CitiesRepository {

    @Autowired
    JdbcClient jdbcClient;

    @Transactional
    public void saveCity(Integer pStateId, CitiesDTO pCitiesDTO) {

        String query = """
                INSERT INTO cep.cidades (
                    id_estado,
                    cidade,
                    codigo_ibge
                )
                VALUES (
                    :estadoId,
                    :cidade,
                    :codigoIbge
                );
                """;

        jdbcClient.sql(query)
                .param("estadoId", pStateId)
                .param("cidade", pCitiesDTO.getNome())
                .param("codigoIbge", pCitiesDTO.getCodigoIbge())
                .update();
    }
}