package br.com.wisetechnology.cep_api.service.cities;

import br.com.wisetechnology.cep_api.model.cities.CitiesDTO;
import br.com.wisetechnology.cep_api.model.state.StateDTO;
import br.com.wisetechnology.cep_api.repository.cities.CitiesRepository;
import br.com.wisetechnology.cep_api.repository.state.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Slf4j
@Service
public class CitiesService {

    @Autowired
    WebClient webClient;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CitiesRepository citiesRepository;

    public void processCitiesData(List<StateDTO> pStatesDTO) {
        try {
            for (StateDTO stateDTO : pStatesDTO) {
                Integer stateId = stateRepository.findStateIdBySigla(stateDTO.getSigla());
                List<CitiesDTO> cities = getAllCitiesByState(stateDTO);

                cities.forEach(city -> saveCity(stateId, city));
                log.info("[Cidades] - As Cidades do Estado: {}, foram salvas com sucesso!", stateDTO.getNome());
            }

            log.info("[Cidades] - Todas as Cidades foram salvas com sucesso!");
        } catch (Exception ex) {
            log.error("[Cidades] - Ocorreu um erro ao tentar salvar as Cidades! - Detalhes: ", ex);
        }
    }

    private List<CitiesDTO> getAllCitiesByState(StateDTO pStatesDTO) {
        log.info("[Cidades] - Buscando informações das cidades do Estado: {}", pStatesDTO.getNome());
        String url = String.format("https://brasilapi.com.br/api/ibge/municipios/v1/%s?providers=dados-abertos-br,gov,wikipedia", pStatesDTO.getSigla());

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(CitiesDTO.class)
                .collectList()
                .block();
    }

    private void saveCity(Integer pStateId, CitiesDTO pCitiesDTO) {
        citiesRepository.saveCity(pStateId, pCitiesDTO);
    }
}