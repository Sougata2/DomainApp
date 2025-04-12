package com.sougata.domainApp.master.controller;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/district")
public class DistrictController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DistrictService service;

    @GetMapping
    public ResponseEntity<List<DistrictDto>> getDistricts() {
        try {
            return ResponseEntity.ok(service.findAllActiveDistricts());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/mapped")
    public ResponseEntity<List<DistrictDto>> getMappedDistricts() {
        try {
            return ResponseEntity.ok(service.findAllMappedDistricts());
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

    @PostMapping("/map")
    public ResponseEntity<DistrictDto> mapDistrict(@RequestBody DistrictDto dto) {
        logger.info("mapDistrict : {}", dto);
        try {
            return ResponseEntity.ok(service.createDistrict(dto));
        } catch (Exception e) {
            logger.error("mapDistrict : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
