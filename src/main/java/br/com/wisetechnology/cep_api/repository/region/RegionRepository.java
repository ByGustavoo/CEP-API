package br.com.wisetechnology.cep_api.repository.region;

import br.com.wisetechnology.cep_api.model.region.RegionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RegionRepository {

    @Autowired
    JdbcClient jdbcClient;

    @Transactional
    public void saveRegions(RegionDTO pRegionsDTO) {

        String query = """
                INSERT INTO cep.regioes (sigla, nome)
                VALUES ( ?, ? );
                """;

        jdbcClient.sql(query)
                .param(pRegionsDTO.getSigla())
                .param(pRegionsDTO.getNome())
                .update();
    }
}