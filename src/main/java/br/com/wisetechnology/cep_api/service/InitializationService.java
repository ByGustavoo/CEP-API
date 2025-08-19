package br.com.wisetechnology.cep_api.service;

import br.com.wisetechnology.cep_api.service.region.RegionService;
import br.com.wisetechnology.cep_api.service.state.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InitializationService {

    @Autowired
    StateService stateService;

    @Autowired
    RegionService regionService;

    @Scheduled(initialDelay = 60000)
    public void setUp() {
        log.info("Iniciando processo de configuração do Sistema!");
        var states = stateService.getAllStates();

        regionService.processRegionData(states);

        stateService.processStateData(states);
    }
}