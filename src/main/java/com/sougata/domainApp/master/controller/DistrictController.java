package com.sougata.domainApp.master.controller;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.entity.CityEntity;
import com.sougata.domainApp.master.entity.DistrictEntity;
import com.sougata.domainApp.master.service.DistrictService;
import com.sougata.domainApp.shared.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/district")
@RequiredArgsConstructor
public class DistrictController {
    private final DistrictService districtService;

    @GetMapping("/convert")
    public ResponseEntity<DistrictEntity> convertEntityToDto() {
        DistrictDto dto = districtService.findDistrictWithActiveCities(UUID.fromString("6453cecd-587d-4a41-b771-21ba80714774"));

        DistrictEntity recursiveMapEntity = Mapper.mapToEntity(dto, DistrictEntity.class, new HashMap<>(Map.of(DistrictDto.CityDto.class, CityEntity.class)));
        System.out.println(recursiveMapEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<DistrictDto> findAllDistrictsWithCity() {
        return districtService.findAllDistrictsWithCity();
    }

    @PostMapping
    public DistrictDto saveDistrict(@RequestBody DistrictDto dto) {
        return districtService.create(dto);
    }

    @PutMapping
    public ResponseEntity<DistrictDto> updateDistrict(@RequestBody DistrictDto dto) {
        UUID distId = districtService.update(dto).distId();
        return ResponseEntity.ok(districtService.findDistrictWithActiveCities(distId));
    }

    @DeleteMapping
    public ResponseEntity<DistrictDto> deleteDistrict(@RequestBody DistrictDto dto) {
        UUID distId = districtService.delete(dto).distId();
        return ResponseEntity.ok(districtService.findDistrictWithActiveCities(distId));
    }
}
