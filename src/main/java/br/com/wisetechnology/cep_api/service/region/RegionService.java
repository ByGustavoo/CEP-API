package br.com.wisetechnology.cep_api.service.region;

import br.com.wisetechnology.cep_api.model.region.RegionDTO;
import br.com.wisetechnology.cep_api.model.state.StateDTO;
import br.com.wisetechnology.cep_api.repository.region.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public void processRegionData(List<StateDTO> pStatesDTO) {
        try {
            saveRegions(getAllRegions(pStatesDTO));
        } catch (Exception ex) {
            log.error("[Regiões] - Ocorreu um erro ao tentar salvar as Regiões - Detalhes: ", ex);
        }
    }

    private List<RegionDTO> getAllRegions(List<StateDTO> pStatesDTO) {
        log.info("[Regiões] - Filtrando as Regiões...");

        return pStatesDTO.stream()
                .map(StateDTO::getRegiao)
                .distinct()
                .sorted(Comparator.comparing(RegionDTO::getNome))
                .toList();
    }

    private void saveRegions(List<RegionDTO> pRegionsDTO) {
        log.info("[Regiões] - Salvando informações de todas as Regiões...");
        pRegionsDTO.forEach(regionRepository::saveRegions);
        log.info("[Regiões] - Regiões salvas com sucesso!");
    }
}