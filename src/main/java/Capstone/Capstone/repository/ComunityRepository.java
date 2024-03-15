package Capstone.Capstone.repository;

import Capstone.Capstone.dto.ComunityDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunityRepository {

    private final Map<Long, ComunityDto> comunityMap = new HashMap<>();
    private Long nextId = 1L;


    public List<ComunityDto> findAll() {
        return new ArrayList<>(comunityMap.values());
    }


    public ComunityDto findById(Long id) {
        return comunityMap.get(id);
    }

    public ComunityDto save(ComunityDto comunityDto) {
        comunityDto.setId(nextId++);
        comunityMap.put(comunityDto.getId(), comunityDto);
        return comunityDto;
    }


    public ComunityDto update(Long id, ComunityDto comunityDto) {
        if (!comunityMap.containsKey(id)) {
            return null;
        }
        comunityDto.setId(id);
        comunityMap.put(id, comunityDto);
        return comunityDto;
    }

    public void delete(Long id) {
        comunityMap.remove(id);
    }
}



