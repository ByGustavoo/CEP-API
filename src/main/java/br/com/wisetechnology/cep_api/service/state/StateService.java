package br.com.wisetechnology.cep_api.service.state;

import br.com.wisetechnology.cep_api.model.state.StateDTO;
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

    public List<StateDTO> getAllStates() {
        log.info("[Estados] - Buscando informações de todos os Estados...");
        String url = "https://brasilapi.com.br/api/ibge/uf/v1";

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(StateDTO.class)
                .collectList()
                .block();
    }
}