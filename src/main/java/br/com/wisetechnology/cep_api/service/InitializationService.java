package br.com.wisetechnology.cep_api.service;

import br.com.wisetechnology.cep_api.model.state.StateDTO;
import br.com.wisetechnology.cep_api.service.cities.CitiesService;
import br.com.wisetechnology.cep_api.service.region.RegionService;
import br.com.wisetechnology.cep_api.service.state.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Comparator;

@Slf4j
@Service
public class InitializationService {

    @Autowired
    StateService stateService;

    @Autowired
    RegionService regionService;

    @Autowired
    CitiesService citiesService;

    @Scheduled(initialDelay = 60000)
    public void setUp() {
        log.info("[Sistema] - Iniciando Processo de Configuração do Sistema!");
        var states = stateService.getAllStates().stream()
                .sorted(Comparator.comparing(StateDTO::getNome))
                .toList();

        regionService.processRegionData(states);
        stateService.processStateData(states);
        citiesService.processCitiesData(states);

        log.info("[Sistema] - Configuração do Sistema realizada com sucesso!");
    }
}