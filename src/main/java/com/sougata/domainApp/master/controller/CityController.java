package com.sougata.domainApp.master.controller;

import com.sougata.domainApp.master.dto.CityDto;
import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.service.CityService;
import com.sougata.domainApp.master.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService service;
    private final DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<CityDto>> getAll() {
        try {
            return ResponseEntity.ok(service.findAllActiveCities());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/not-mapped-to-dist")
    public ResponseEntity<List<CityDto>> getNotMappedToDistricts(@RequestParam Long dist) {
        try {
            DistrictDto foundDistrict = districtService.findDistrictById(dist);
            if (foundDistrict == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(service.findCitiesNotMappedToDist(foundDistrict));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<CityDto> create(@RequestBody CityDto city) {
        try {
            return ResponseEntity.ok(service.createCity(city));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
