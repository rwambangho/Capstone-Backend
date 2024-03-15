package Capstone.Capstone.service;
import Capstone.Capstone.dto.ComunityDto;

import java.util.List;


public interface ComunityService {
    List<ComunityDto> findAll();
    ComunityDto findById(Long id);
    ComunityDto save(ComunityDto comunityDto);
    ComunityDto update(Long id, ComunityDto comunityDto);
    void delete(Long id);
}
