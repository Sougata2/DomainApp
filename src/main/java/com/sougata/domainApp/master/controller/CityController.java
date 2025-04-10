package com.sougata.domainApp.master.controller;

import com.sougata.domainApp.master.dto.CityDto;
import com.sougata.domainApp.master.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping
    public ResponseEntity<List<CityDto>> getAll() {
        try {
            return ResponseEntity.ok(service.findAllActiveCities());
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
