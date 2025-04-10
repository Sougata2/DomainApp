package com.sougata.domainApp.master.controller;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/district")
public class DistrictController {
    private final DistrictService service;

    @GetMapping
    public ResponseEntity<List<DistrictDto>> getDistricts() {
        try {
            return ResponseEntity.ok(service.findAllActiveDistricts());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<DistrictDto> createDistrict(@RequestBody DistrictDto dto) {
        try {
            return ResponseEntity.ok(service.createDistrict(dto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
