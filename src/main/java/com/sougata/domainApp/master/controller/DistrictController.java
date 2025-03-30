package com.sougata.domainApp.master.controller;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/district")
@RequiredArgsConstructor
public class DistrictController {
    private final DistrictService districtService;

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
