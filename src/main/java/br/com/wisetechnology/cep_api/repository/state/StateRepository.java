package br.com.wisetechnology.cep_api.repository.state;

import br.com.wisetechnology.cep_api.model.state.StateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StateRepository {

    @Autowired
    JdbcClient jdbcClient;

    @Transactional
    public void saveState(StateDTO pStateDTO) {

        Integer regionId = findRegionIdBySigla(pStateDTO.getRegiao().getSigla());

        String query = """
                INSERT INTO cep.estados (
                    id_regiao,
                    sigla,
                    nome
                )
                VALUES (
                    :regionId,
                    :sigla,
                    :nome
                );
                """;

        jdbcClient.sql(query)
                .param("regionId", regionId)
                .param("sigla", pStateDTO.getSigla())
                .param("nome", pStateDTO.getNome())
                .update();
    }

    public Integer findStateIdBySigla(String pSigla) {

        String query = """
                SELECT id
                FROM cep.estados e
                WHERE e.sigla LIKE :pSigla;
                """;

        return jdbcClient.sql(query)
                .param("pSigla", pSigla)
                .query(Integer.class)
                .single();
    }

    private Integer findRegionIdBySigla(String pSigla) {

        String query = """
                SELECT id
                FROM cep.regioes r
                WHERE r.sigla LIKE :pSigla;
                """;

        return jdbcClient.sql(query)
                .param("pSigla", pSigla)
                .query(Integer.class)
                .single();
    }
}