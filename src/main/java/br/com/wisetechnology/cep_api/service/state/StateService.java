package br.com.wisetechnology.cep_api.service.state;

import br.com.wisetechnology.cep_api.model.state.StateDTO;
import br.com.wisetechnology.cep_api.repository.state.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Slf4j
@Service
public class StateService {

    @Autowired
    WebClient webClient;

    @Autowired
    StateRepository stateRepository;

    public void processStateData(List<StateDTO> pStatesDTO) {
        try {
            saveState(pStatesDTO);
        } catch (Exception ex) {
            log.error("[Estados] - Ocorreu um erro ao tentar salvar os Estados! - Detalhes: ", ex);
        }
    }

    public List<StateDTO> getAllStates() {
        String url = "https://brasilapi.com.br/api/ibge/uf/v1";
        log.info("[Estados] - Buscando informações de todos os Estados...");

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(StateDTO.class)
                .collectList()
                .block();
    }

    private void saveState(List<StateDTO> pStatesDTO) {
        log.info("[Estados] - Salvando informações de todos os Estados...");
        pStatesDTO.forEach(stateRepository::saveState);
        log.info("[Estados] - Estados salvos com sucesso!");
    }
}